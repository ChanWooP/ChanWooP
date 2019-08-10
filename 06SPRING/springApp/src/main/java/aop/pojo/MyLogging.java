package aop.pojo;

import org.aspectj.lang.JoinPoint;

public class MyLogging {
	public String beforeLogging(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName+" �޼ҵ� ȣ�� �� ...");
		
		return methodName;
	}
	
	public void returningLogging(JoinPoint jp, Object ret) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName+
				" �޼ҵ� ȣ�� ��(���� ���� ���������� �޼ҵ尡 ����� ��� ..., ���ϰ� : "+ret);
	}
	
	public void throwingLogging(JoinPoint jp, Throwable ex) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName + 
				" �޼ҵ忡�� ���ܰ� �߻��� ���..., ����Ŭ���� : " + ex.getClass().getName());
	}
	
	public void afterLogging(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		System.out.println(methodName +" �޼ҵ忡�� ���ܹ߻����ο� ������� ȣ��...");
	}
}


