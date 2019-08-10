package aop.pojo;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

/*
 * - AfterReturningAdvice �������̽�
 *   ��� ��ü�� �޼ҵ带 ���� ���� ���� ����� �����Ҷ� ����ϴ� Advice(���� ���� ����� ��츸)
 */
public class AfterLogAdvice implements AfterReturningAdvice {
	private Log log= LogFactory.getLog(getClass());

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		String s=method.getName() +" �޼ҵ� ������ ...";
		if(args!=null && args.length>0) {
			s+=", Argument : ";
			for(int i=0; i<args.length; i++) {
				s+=args[i]+"  ";
			}
		}
		
		s+=", ���ϰ� : " + returnValue;
		log.info(s);
	}
}
