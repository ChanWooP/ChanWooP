package com.anno6;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		// ������ �����̳� ����
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("classpath:com/anno6/applicationContext.xml");
		
		try {
			Movie movie1 = context.getBean(Movie.class);
			Movie movie2 = context.getBean(Movie.class);
			// �⺻ : singleton
			System.out.println("��ȭ : " + (movie1==movie2)); // true
			movie1.play();
			movie2.play();
			
			Music music1 = context.getBean(Music.class);
			Music music2 = context.getBean(Music.class);
			// prototype
			System.out.println("���� : " + (music1==music2)); // false
			music1.play();
			music2.play();
			
		} finally {
			context.close();
		}

	}

}
