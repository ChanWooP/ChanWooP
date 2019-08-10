package com.anno5;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		// ������ �����̳� ����
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("classpath:com/anno5/applicationContext.xml");
		
		try {
			// ������ �����̳ʿ��� ��ü�� ������
			UserBean user = (UserBean)context.getBean("userBean");
			user.write();
		} finally {
			context.close();
		}

	}

}
