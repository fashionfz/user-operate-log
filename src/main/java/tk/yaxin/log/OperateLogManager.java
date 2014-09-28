package tk.yaxin.log;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

public class OperateLogManager {

	private String scan;
	/** 
	 * @return scan 
	 */
	public String getScan() {
		return scan;
	}
	/** 
	 * @param scan 要设置的 scan 
	 */
	public void setScan(String scan) {
		this.scan = scan;
	}

	public OperateLogManager(){
		String xx = scan;
		try {
			modifyMethodAnnotation("tk.yaxin.log.OperateLogAOP","pointcut","xxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @Title: modifyClassAnnotation 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param aopClassName
	 * @param point
	 * @return
	 */
	@SuppressWarnings("unused")
	private Class<?> modifyClassAnnotation(String aopClassName, String point){
        Class<?> c = null;  
        try {  
            ClassPool classPool = ClassPool.getDefault();  
            classPool.appendClassPath(new ClassClassPath(OperateLogManager.class));  
            classPool.importPackage("tk.yaxin.log");  
            CtClass clazz = classPool.get(aopClassName);  
            ClassFile classFile = clazz.getClassFile();  
            ConstPool constPool = classFile.getConstPool();  
            Annotation aopAnnotation = new Annotation("org.aspectj.lang.annotation.Pointcut",constPool);  
            aopAnnotation.addMemberValue("value", new StringMemberValue(point, constPool));  
            AnnotationsAttribute attribute = (AnnotationsAttribute)classFile.getAttribute(AnnotationsAttribute.visibleTag);  
            attribute.addAnnotation(aopAnnotation);
            classFile.addAttribute(attribute);  
            classFile.setVersionToJava5();
            c = clazz.toClass();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        return c; 
	}
	
	/**
	 * 
	 * @Title: modifyMethodAnnotation 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param aopClassName
	 * @param methodName
	 * @param point
	 * @return
	 */
	private Class<?> modifyMethodAnnotation(String aopClassName,String methodName, String point){
        Class<?> c = null;  
        try {  
            ClassPool classPool = ClassPool.getDefault();  
            classPool.appendClassPath(new ClassClassPath(OperateLogManager.class));  
            classPool.importPackage("tk.yaxin.log");  
            CtClass clazz = classPool.get(aopClassName);  
            ClassFile classFile = clazz.getClassFile(); 
            CtMethod methods = clazz.getDeclaredMethod("pointcut");
            ConstPool constPool = classFile.getConstPool();  
            Annotation aopAnnotation = new Annotation("org.aspectj.lang.annotation.Pointcut",constPool);  
            aopAnnotation.addMemberValue("value", new StringMemberValue(point, constPool));  
            MethodInfo method = methods.getMethodInfo();
            AnnotationsAttribute attribute = (AnnotationsAttribute)method.getAttribute(AnnotationsAttribute.visibleTag);  
            attribute.addAnnotation(aopAnnotation);
            classFile.addAttribute(attribute);  
            classFile.setVersionToJava5();
            c = clazz.toClass();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        return c; 
	}
}
