package aop.pojo;

import org.aspectj.lang.JoinPoint;

public class MyLogging {
	public String beforeLogging(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName+" 메소드 호출 전 ...");
		
		return methodName;
	}
	
	public void returningLogging(JoinPoint jp, Object ret) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName+
				" 메소드 호출 후(예외 없이 성공적으로 메소드가 실행된 경우 ..., 리턴값 : "+ret);
	}
	
	public void throwingLogging(JoinPoint jp, Throwable ex) {
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName + 
				" 메소드에서 예외가 발생한 경우..., 예외클래스 : " + ex.getClass().getName());
	}
	
	public void afterLogging(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		System.out.println(methodName +" 메소드에서 예외발생여부와 상관없이 호출...");
	}
}


