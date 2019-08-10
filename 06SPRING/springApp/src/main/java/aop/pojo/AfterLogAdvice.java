package aop.pojo;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

/*
 * - AfterReturningAdvice 인터페이스
 *   대상 객체의 메소드를 실행 이후 공통 기능을 실행할때 사용하는 Advice(예외 없이 실행된 경우만)
 */
public class AfterLogAdvice implements AfterReturningAdvice {
	private Log log= LogFactory.getLog(getClass());

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		String s=method.getName() +" 메소드 실행후 ...";
		if(args!=null && args.length>0) {
			s+=", Argument : ";
			for(int i=0; i<args.length; i++) {
				s+=args[i]+"  ";
			}
		}
		
		s+=", 리턴값 : " + returnValue;
		log.info(s);
	}
}
