package aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/*
 * MethodInterceptor
 *   메소드 실행 전, 실행 후, 예외발생시점에 공통기능을 실행 할 수 있다.
 *   MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice 세가지 Advice를 하나로 묶은 Advice
 */
public class InterceptorLogAdvice  implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// 대상 메소드 호출 전
		String s;
		s = invocation.getMethod().getName()+" 메소드에서 호출전 ...";
		System.out.println(s);
		
		// 대상 객체 메소드 호출
		Object returnValue = invocation.proceed();
		
		// 대상 메소드 호출 후
		s = invocation.getMethod().getName()+" 메소드에서 호출후 ...";
		s+=", 리턴값 : " + returnValue;
		System.out.println(s);
		
		return returnValue;
	}

}
