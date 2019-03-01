package com.test2;

//실생활에 존재하는 대상을 
//객체 지향적 표현
//->클래스
public class Printer {
	//state - ink
	int ink = 200;
	
	//behavior - print
	void print(String msg) {
		if(ink > 0) {
			System.out.println(msg);
			ink -= 10;
		}else {
			System.out.println("잉크부족");
		}
	}
}
