package com.test102;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {
		//물리적 저장된 환경설정 정보 -> test.properties
		
		Properties prop = new Properties();
		//파일을 불러온다
		try {
			prop.load(new FileInputStream("src/com/test102/test.properties"));
			
			Enumeration<?> e = prop.propertyNames();
			while(e.hasMoreElements()) {
				String key = (String) e.nextElement();
				System.out.printf("%s / %s%n", key, prop.getProperty(key));
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
