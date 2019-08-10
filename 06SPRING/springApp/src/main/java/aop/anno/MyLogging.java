package aop.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/*
 * @Aspect
 *    설정파일에 Advice, Pointcut등을 설정하지 않고 자동으로 Advice를 적용
 */

@Aspect
public class MyLogging {
	@Before("execution(public * aop.anno.*.*(..))")
	public String beforeLogging(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName+" 메소드 호출 전 ...");
		
		return methodName;
	}
	
	@AfterReturning(pointcut="execution(public * aop.anno.*.*(..))", returning="ret")
	public void returningLogging(JoinPoint jp, Object ret) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName+
				" 메소드 호출 후(예외 없이 성공적으로 메소드가 실행된 경우 ..., 리턴값 : "+ret);
	}
	
	@AfterThrowing(pointcut="execution(public * aop.anno.*.*(..))", throwing="ex")
	public void throwingLogging(JoinPoint jp, Throwable ex) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName + 
				" 메소드에서 예외가 발생한 경우..., 예외클래스 : " + ex.getClass().getName());
	}
	
	@After("execution(public * aop.anno.*.*(..))")
	public void afterLogging(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		System.out.println(methodName +" 메소드에서 예외발생여부와 상관없이 호출...");
	}
}


