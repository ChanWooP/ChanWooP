package aop.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/*
 * @Aspect
 *    �������Ͽ� Advice, Pointcut���� �������� �ʰ� �ڵ����� Advice�� ����
 */

@Aspect
public class MyLogging {
	@Before("execution(public * aop.anno.*.*(..))")
	public String beforeLogging(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName+" �޼ҵ� ȣ�� �� ...");
		
		return methodName;
	}
	
	@AfterReturning(pointcut="execution(public * aop.anno.*.*(..))", returning="ret")
	public void returningLogging(JoinPoint jp, Object ret) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName+
				" �޼ҵ� ȣ�� ��(���� ���� ���������� �޼ҵ尡 ����� ��� ..., ���ϰ� : "+ret);
	}
	
	@AfterThrowing(pointcut="execution(public * aop.anno.*.*(..))", throwing="ex")
	public void throwingLogging(JoinPoint jp, Throwable ex) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName + 
				" �޼ҵ忡�� ���ܰ� �߻��� ���..., ����Ŭ���� : " + ex.getClass().getName());
	}
	
	@After("execution(public * aop.anno.*.*(..))")
	public void afterLogging(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		System.out.println(methodName +" �޼ҵ忡�� ���ܹ߻����ο� ������� ȣ��...");
	}
}


