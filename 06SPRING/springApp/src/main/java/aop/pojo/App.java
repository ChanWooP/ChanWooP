package aop.pojo;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		// ������ �����̳� ����
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("classpath:aop/pojo/applicationContext.xml");
		
		try {
			UserService service = (UserService)context.getBean("userService");
			service.save("����");
			service.write();
		} finally {
			context.close();
		}

	}

}
