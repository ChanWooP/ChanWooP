package com.anno8;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		// ������ �����̳� ����
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("classpath:com/anno8/applicationContext.xml");
		
		try {
			// ������ �����̳ʿ��� ��ü�� ������
			UserBean user = context.getBean(UserBean.class);
			user.write();
		} finally {
			context.close();
		}

	}

}
