package aop.anno2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/*
 * @Aspect
 *    �������Ͽ� Advice, Pointcut���� �������� �ʰ� �ڵ����� Advice�� ����
 */

@Aspect
@Service
public class MyLogging {
	@Pointcut(value="execution(public * aop.anno2.*.*(..))")
	private void allMethod() {
		// ����޼ҵ� : ����Ÿ���� void �̰� ��ü�� ����� �Ѵ�.
	}
	
	@Before("allMethod()")
	public String beforeLogging(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName+" �޼ҵ� ȣ�� �� ...");
		
		return methodName;
	}
	
	@AfterReturning(value="allMethod()", returning="ret")
	public void returningLogging(JoinPoint jp, Object ret) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName+
				" �޼ҵ� ȣ�� ��(���� ���� ���������� �޼ҵ尡 ����� ��� ..., ���ϰ� : "+ret);
	}
	
	@AfterThrowing(value="allMethod()", throwing="ex")
	public void throwingLogging(JoinPoint jp, Throwable ex) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName + 
				" �޼ҵ忡�� ���ܰ� �߻��� ���..., ����Ŭ���� : " + ex.getClass().getName());
	}
	
	@After(value="allMethod()")
	public void afterLogging(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		System.out.println(methodName +" �޼ҵ忡�� ���ܹ߻����ο� ������� ȣ��...");
	}
}
