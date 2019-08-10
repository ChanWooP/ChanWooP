package com.anno2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		// 스프링 컨테이너 생성
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("classpath:com/anno2/applicationContext.xml");
		
		try {
			// 스프링 컨테이너에서 빈객체를 가져옴
			UserBean user = (UserBean)context.getBean("userBean");
			user.write();
			
			UserBean2 user2 = context.getBean(UserBean2.class);
			user2.write();
			
		} finally {
			context.close();
		}

	}

}
