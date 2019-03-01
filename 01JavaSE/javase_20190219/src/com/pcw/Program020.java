/*
 * 프로그램명 : 출력 액션
 * 작성자 : 박찬우
 * 작성일 : 20190219
 * 프로그램기능 : 각종 출력 설명
 */
package com.pcw;

public class Program020 {

	public static void main(String[] args) {
		//문자열 출력 액션 메소드
		//print(), println(), printf()
		
		int a = 10, b = 20;
		
		System.out.print(a+"\n");//10
		System.out.print(b+"\n");//20
		
		System.out.print(a+b+"\n");//30
		System.out.print(a + ", " + b +"\n");//10, 20
		
		System.out.println(a);
		System.out.println(b);
		
		System.out.printf("%d, %d%n", a,b);
		System.out.printf("%d, %d", a,b);
	}

}
