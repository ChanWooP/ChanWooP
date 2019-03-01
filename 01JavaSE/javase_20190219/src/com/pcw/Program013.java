/*
 * 프로그램명 : 변수
 * 작성자 : 박찬우
 * 작성일 : 20190219
 * 프로그램기능 : 변수 설명
 */

package com.pcw;

public class Program013 {

	public static void main(String[] args) {
		//변수 
		//메모리에 자료를 저장할 수 있는 임시 공간에 대한 별칭
		//특정 자료형을 지저애서 변수 선언
		//1. Local variavles
		//2. Instance variavles -> 자동 초기화 지원
		//3. Class/Static variavles -> 자동 초기화 지원
		
		//1. Local variavles
		//특정 영역(변수가 선언된 블럭 내) 에서만 활동할 수 있는 변수
		//특정 영역은 메소드블럭, if블럭, for 블럭 등으로 제한
		//사용 전에 초기화 필요
		
		//메소드 내에서 선언된 경우
		int a = 10;
		
		//변수 a의 값을 화면에 출력한다
		System.out.println(a);
	}

}
