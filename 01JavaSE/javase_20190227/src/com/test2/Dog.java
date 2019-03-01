package com.test2;

//실생활에 존재하는 대상을 
//객체 지향적 표현
//->클래스
//->예름 들어, 강아지를 표현한다면 class Dog{}
public class Dog {
	//state(프로퍼티)
	//behavior(메소드)
	
	//state - name
	//변수 선언된 위치에 의해서 Instance variable
	String name = "Puppy";
	
	//behavior - barking
	//void형의 메소드 선언
	void barking(){
		//코드 작성
		System.out.println("멍멍");
	}
	
}
