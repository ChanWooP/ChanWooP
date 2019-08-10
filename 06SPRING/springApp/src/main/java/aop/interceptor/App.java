package aop.interceptor;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		// 스프링 컨테이너 생성
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("classpath:aop/interceptor/applicationContext.xml");
		
		try {
			UserService service = (UserService)context.getBean("userService");
			service.save("예제");
			service.write();
		} finally {
			context.close();
		}

	}

}
