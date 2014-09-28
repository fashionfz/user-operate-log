package tk.yaxin.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class OperateLogAOP {
	
	@Pointcut("execution(* com.wicresoft.app.service.impl.*.*(..))")  
	public void pointcut(){}  

	@Before("execution(* com.wicresoft.app.service.impl.*.*(..))")  
	public void before(JoinPoint point){
		String className = point.getTarget().getClass().getSimpleName();
		String method = point.getSignature().getName();
		Object[] args = point.getArgs();
		System.out.println(className+":"+method+":");
		
	}
	
	@After("execution(* com.wicresoft.app.service.impl.*.*(..))")  
	public void after(JoinPoint point){
		String className = point.getTarget().getClass().getSimpleName();
		String method = point.getSignature().getName();
		Object[] args = point.getArgs();
		
		System.out.println(className+":"+method+":");
		
	}
	
	@AfterReturning(returning="rvt", pointcut="pointcut")  
    public void log(Object rvt) {  
        System.out.println("模拟目标方法返回值：" + rvt);  
        System.out.println("模拟记录日志功能...");  
    }  
	
	
	 @AfterThrowing(throwing="ex", pointcut="pointcut")  
    public void doRecoverActions(Throwable ex) {  
        System.out.println("目标方法中抛出的异常：" + ex);  
        System.out.println("模拟抛出异常后的增强处理...");  
    }  
	 
	@Around("execution(* com.wicresoft.app.service.impl.*.*(..))")  
    public Object processTx(ProceedingJoinPoint jp) throws java.lang.Throwable {  
        System.out.println("执行目标方法之前，模拟开始事物...");  
        // 执行目标方法，并保存目标方法执行后的返回值  
        Object rvt = jp.proceed(new String[]{"被改变的参数"});  
        System.out.println("执行目标方法之前，模拟结束事物...");  
        return rvt + "新增的内容";  
    }  
}
