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
 *    설정파일에 Advice, Pointcut등을 설정하지 않고 자동으로 Advice를 적용
 */

@Aspect
@Service
public class MyLogging {
	@Pointcut(value="execution(public * aop.anno2.*.*(..))")
	private void allMethod() {
		// 가명메소드 : 리턴타입은 void 이고 몸체가 없어야 한다.
	}
	
	@Before("allMethod()")
	public String beforeLogging(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName+" 메소드 호출 전 ...");
		
		return methodName;
	}
	
	@AfterReturning(value="allMethod()", returning="ret")
	public void returningLogging(JoinPoint jp, Object ret) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName+
				" 메소드 호출 후(예외 없이 성공적으로 메소드가 실행된 경우 ..., 리턴값 : "+ret);
	}
	
	@AfterThrowing(value="allMethod()", throwing="ex")
	public void throwingLogging(JoinPoint jp, Throwable ex) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName + 
				" 메소드에서 예외가 발생한 경우..., 예외클래스 : " + ex.getClass().getName());
	}
	
	@After(value="allMethod()")
	public void afterLogging(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		System.out.println(methodName +" 메소드에서 예외발생여부와 상관없이 호출...");
	}
}
