package com.test050;

public class Main {

	public static void main(String[] args) {
		Sample s = new Sample();
		
		//System.out.println(s.a); - private로 선언해서 클래스 외부에서 접근 불가능함
		//s.method1(); - private로 선언해서 클래스 외부에서 접근 불가능함
		s.method2();
		s.method3();
	}

}
