package aop.before;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;

/*
 * - MethodBeforeAdvice 인터페이스
 *   대상 객체의 메소드를 실행하기 전에 공통 기능을 실행할때 사용하는 Advice
 */
public class BeforeLogAdvice implements MethodBeforeAdvice {
	private Log log= LogFactory.getLog(getClass());
	
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String s=method.getName() +" 메소드 : " + target +"에서 호출하기전...";
		if(args!=null && args.length>0) {
			s+="\nArgument : ";
			for(int i=0; i<args.length; i++) {
				s+=args[i]+"  ";
			}
		}
		
		log.info(s);
		
		System.out.println(s);
	}

}
