package aop.before;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;

/*
 * - MethodBeforeAdvice �������̽�
 *   ��� ��ü�� �޼ҵ带 �����ϱ� ���� ���� ����� �����Ҷ� ����ϴ� Advice
 */
public class BeforeLogAdvice implements MethodBeforeAdvice {
	private Log log= LogFactory.getLog(getClass());
	
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String s=method.getName() +" �޼ҵ� : " + target +"���� ȣ���ϱ���...";
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
