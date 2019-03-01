package com.test2;

public class Main {

	public static void main(String[] args) {
		//Dog 클래스에 대한 인스턴스 생성
		//-> new 연산자
		
		//클래스명 변수 = new 클래스명();
		//클래스는 참조형 자료
		//변수는 스택 영역에 적재
		//변수에 힙 영역의 참조주소가 전달된다
		Dog dog1 = new Dog();
		
		//인스터스 확인
		//패키지명.클래스명@해쉬코드
		System.out.println(dog1);
		
		//인스턴스의 메소드 호출
		//->메소드 내의 액션 코드 실행
		dog1.barking(); // 멍멍
		System.out.println();
		
		//-----------------------------------------
		
		Printer p1 = new Printer();
		
		System.out.println(p1);
		
		for(int a=0; a<21; ++a) {
			System.out.printf("%2d)",a+1);
			p1.print("test print");
		}
		
	}

}
