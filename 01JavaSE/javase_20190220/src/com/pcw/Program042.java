/*
 * 프로그램명 : Math 클래스
 * 작성자 : 박찬우
 * 작성일 : 20190220
 * 프로그램기능 : Math 클래스 설명
 */
package com.pcw;

public class Program042 {

	public static void main(String[] args) throws InterruptedException {
		//난수 발생
		//Math 클래스의 random() 메소드를 이용하면 난수 발생 가능
		//난수 기본 범위 0 이상 1미만의 실수  0.0부터 0.99999~ 까지만 발생
		double a;
		a=Math.random();
		
		//기본 난수 범위
		System.out.printf("%f%n", a);//0.0 ~ 0.999~
		
		//특정 범위 난수(주사위 1~6)
		System.out.println(a*6);//0.0 ~ 5.999~
		System.out.println((int)(a*6));//0 ~ 5 - 형변환(실수 -> 정수)
		System.out.println((int)(a*6)+1);//1 ~ 6
		
		while(true) {
			int b1 = (int)(Math.random() * 6)+1; // 난수 값을 저장하기
			int b2 = (int)(Math.random() * 6)+1;
			System.out.printf("%n(%d, %d)", b1,b2);
			Thread.sleep(1000);
		}
		
	}

}
