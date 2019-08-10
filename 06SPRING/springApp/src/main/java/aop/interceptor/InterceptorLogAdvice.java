package aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/*
 * MethodInterceptor
 *   �޼ҵ� ���� ��, ���� ��, ���ܹ߻������� �������� ���� �� �� �ִ�.
 *   MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice ������ Advice�� �ϳ��� ���� Advice
 */
public class InterceptorLogAdvice  implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// ��� �޼ҵ� ȣ�� ��
		String s;
		s = invocation.getMethod().getName()+" �޼ҵ忡�� ȣ���� ...";
		System.out.println(s);
		
		// ��� ��ü �޼ҵ� ȣ��
		Object returnValue = invocation.proceed();
		
		// ��� �޼ҵ� ȣ�� ��
		s = invocation.getMethod().getName()+" �޼ҵ忡�� ȣ���� ...";
		s+=", ���ϰ� : " + returnValue;
		System.out.println(s);
		
		return returnValue;
	}

}
