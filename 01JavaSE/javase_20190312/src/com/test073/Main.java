package com.test073;

public class Main {

	public static void main(String[] args) {
		
		//객체의 자료형과 변수의 자료형이 불일치
		//서로다른 클래스를 하나로 묶을 수 있다
		// s1,s2 변수는 Super 자료형
        // 가지고 있는 값은 Sample1, Sample2 객체
		Super s1 = new Sample1();
		Super s2 = new Sample2();

		
		
		//공통특성을 활용한 메소드 호출
		//메소드 이름은 같다 결과는 다르다
		s1.method();
		s2.method();
		
		Sample3 s3 = new Sample3();
		s3.method();
	}

}
