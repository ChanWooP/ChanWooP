package com.anno2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		// ������ �����̳� ����
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("classpath:com/anno2/applicationContext.xml");
		
		try {
			// ������ �����̳ʿ��� ��ü�� ������
			UserBean user = (UserBean)context.getBean("userBean");
			user.write();
			
			UserBean2 user2 = context.getBean(UserBean2.class);
			user2.write();
			
		} finally {
			context.close();
		}

	}

}
