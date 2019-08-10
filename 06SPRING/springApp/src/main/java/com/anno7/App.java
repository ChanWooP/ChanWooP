package com.anno7;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		// 스프링 컨테이너 생성
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("classpath:com/anno7/applicationContext.xml");
		
		try {
			UserBean user=context.getBean(UserBean.class);
			
			user.execute();
			user.execute();
			user.execute();
			
		} finally {
			context.close();
		}

	}

}
