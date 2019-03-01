/*
 * 프로그램명 : 자료형 - 기본 자료형
 * 작성자 : 박찬우
 * 작성일 : 20190219
 * 프로그램기능 : 자료형 설명3
 */

package com.pcw;

public class Program011 {

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
		
		//문자 - char
		//변수 a는 char 자료형만큼의 공간(2바이트)을 차지한다
		//char자료형은 작은따옴표(')로 감싼 문자 1개를 의미한다
		//자바에서는 문자는 유니코드를 사용합니다
		char a = 'A';
		char b = 65;
		char c = '\uFFFF'; //유니코드
		long d = 2147483647;
	}

}
