package com.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyPrint{
	String value() default "-";
	int number() default 15;
}

class MyService {
	public void method() {
		System.out.println("안녕 ...");
	}

	@MyPrint
	public void method1() {
		System.out.println("안녕 1...");
	}
	@MyPrint("*")
	public void method2() {
		System.out.println("안녕 2...");
	}
	@MyPrint(value="#", number=20)
	public void method3() {
		System.out.println("안녕 3...");
	}
	
}

public class Test {
	public static void main(String[] args) {
		Method[] mm=MyService.class.getDeclaredMethods();
		
		try {
			for(Method m : mm) {
				System.out.println();
				
				// 어노테이션을 적용했으면
				if(m.isAnnotationPresent(MyPrint.class)) {
					MyPrint mp = m.getAnnotation(MyPrint.class);
					// method name
					System.out.println("["+m.getName()+"]");
					for(int i=0; i<mp.number(); i++) {
						System.out.print(mp.value());
					}
					System.out.println();
				}
				//  메소드 호출
				m.invoke(new MyService());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
