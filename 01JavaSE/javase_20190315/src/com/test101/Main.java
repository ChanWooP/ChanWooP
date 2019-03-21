package com.test101;

import java.util.Enumeration;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {
		
		//자바 프로그램 자신의 환경 정보
		Properties prop = System.getProperties();
		
		//자바 버전 확인
		System.out.println(prop.getProperty("java.version")); 
		
		//순차적 접근
		Enumeration<?> e = prop.propertyNames();
		while(e.hasMoreElements()) {
			String key = (String) e.nextElement();
			System.out.printf("%s / %s%n", key, prop.getProperty(key));
		}
	}

}
