/*
 * 프로그램명 : 자료형 - 기본 자료형
 * 작성자 : 박찬우
 * 작성일 : 20190219
 * 프로그램기능 : 자료형 설명1
 */

package com.pcw;

public class Program009 {

	public static void main(String[] args) {
		//자료형구분
		//1. 기본자료형(Primitive Data Types)
		//2. 참조자료형(Reference/Object Data Types)
		
		//1. 기본자료형
		//정수, 실수, 문자, 부울(논리값)
		//정수 : byte, short, int, long
		//실수 : float, double
		//문자 : char
		//부울 : boolean
		
		//변수 a라는 공간에 100 이라는 값 저장
		//변수 a는 byte 자료형만큼의 공간(1byte)을 차지한다
		byte a = 100;
		
		//변수 b라는 공간에 200 이라는 값 저장
		//변수 b는 short 자료형만큼의 공간(2byte)을 차지한다
		short b = 200;
		
		//변수 c라는 공간에 300 이라는 값 저장
		//변수 c는 int 자료형만큼의 공간(4byte)을 차지한다
		//int는 정수 자료형 중에서 기본 자료형 
		int c = 300;
		
		
		//변수 d라는 공간에 400L 이라는 값 저장
		//변수 d는 long 자료형만큼의 공간(8byte)을 차지한다
		//long자료형 값은 L 접미사 필요
		long d = 400L;
		
	}

}
