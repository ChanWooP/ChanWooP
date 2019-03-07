package com.test053;

public class Main {

	public static void main(String[] args) {
		Sample s1 = new Sample();
		Sample s2 = new Sample();
		
		s1.setB(10); // instance변수 값 10
		s2.setB(20); // instance변수 값 20
		
		s1.setA(10); // static변수 값 10
		s2.setA(20); // static변수 값 20
		// static 맴버는 모든 객체가 공유한다
		// (서로 다른 객체가 변경을 시도해도 마지막으로 시도한 값으로 나온다)
		
		s1.method1(); // 결과 2010
		s2.method1(); // 결과 2020
	}

}
