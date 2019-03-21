package com.test104;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

public class Sample {
	public void method() throws Exception{
		Properties prop = new Properties();
		
		prop.load(new FileInputStream("src/com/test102/test.properties"));
		
		Enumeration<?> e = prop.propertyNames();
		while(e.hasMoreElements()) {
			String key = (String) e.nextElement();
			System.out.printf("%s / %s%n", key, prop.getProperty(key));
		}
	}
}
