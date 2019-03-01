package com.test6;

public class Main {

	public static void main(String[] args) {
		//클래스 선언의 물리적 구성
		//클래스명 = 파일명
		//클래스명 첫 글자 대문자 - 파스칼표기
		//형식
		/*
		 접근제한자 class 클래스명{}
		 */
		//프로퍼티, 메소드 선택적 구성 - 빈 클래스이더라도 기본 구성 요소를 가진다
		//파일 하나에 클래스 하나 배치 권장
		//파일 하나에 클래스 여러개 배치 가능(1개의 클래스에만 public 붙이기 가능) 
		//클래스들은 논리적으로 패키지에 소속되어 있다
		//예를 들어, java.lang, java.util 패키지
		
		//빈 클래스의 구성 요소 확인
		Sample s = new Sample();
		
		//toString()은 모든 클래스가 가지는 기본 요소
		System.out.println(s.toString());
		
		//toString()은 유일하게 생략 가능한 메소드
		System.out.println(s);
	}

}
