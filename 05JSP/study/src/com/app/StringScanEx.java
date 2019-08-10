package com.app;

import java.lang.reflect.Method;

public class StringScanEx {
	public static void main(String[] args) {
		try {
			String s = "java.lang.String";
			Class<?> cls = Class.forName(s);
			
			System.out.println("method...");
			Method[] mm = cls.getMethods();
			for(Method m:mm)
				System.out.println(m);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
