/*
 * 프로그램명 : 외부입력-개인정보 + 성적 처리
 * 작성자 : 박찬우
 * 작성일 : 20190226
 */

package com.test;

public class Program113 {

	public static void main(String[] args) {
		//형변환
		//특정 형을 가진 자료를 다른 형으로 변환
		//예를 들어, double -> int, int -> double
		//기본자료형 - 기본자료형, 참조자료형 - 참조자료형, 기본자료형 -> 참조자료형, 참조자료형 -> 기본자료형
		
		//기본 자료형끼리의 변환시
		//(자료형)값
		
		double a = 5.5; 
		int b = (int)a; // 명시적 형변환
		System.out.println(b);
		
		int c = 5;
		double d = c; // 암시적 형변환(작은자료형 -> 큰자료형)
		System.out.println(d);
		
		int e = 100;
		System.out.println(e+100);
		String f = String.valueOf(e);
		System.out.println(f+100);
		
		String g = "100";
		String h = "90";
		System.out.println(g+h);
		System.out.println(Integer.parseInt(g)+Integer.parseInt(h));
		
		int i = 100;
		int j = 100;
		if(i == j) {
			System.out.println("같은 숫자");
		}else {
			System.out.println("다른 숫자");
		}
		
		String k = new String("hong");
		String l = new String("Hongs");
		
		//메모리상의 참조주소 비교
		if(k == l) {
			System.out.println("같은 문자");
		}else {
			System.out.println("다른 문자");
		}
		
		//대소문자 구별하는 문자 비교
		if(k.equals(l)) {
			System.out.println("같은 문자");
		}else {
			System.out.println("다른 문자");
		}
		
		//대소문자 구별하지 않는 문자 비교
		if(k.equalsIgnoreCase(l)) {
			System.out.println("같은 문자");
		}else {
			System.out.println("다른 문자");
		}
		
		//문자열 크기 비교
		if(k.compareToIgnoreCase(l)>0) {
			System.out.println("k가 l보다 크다");
		}else if(k.compareToIgnoreCase(l)<0){
			System.out.println("k가 l보다 작다");
		}else {
			System.out.println("k와 l이 같다");
		}
		System.out.println(k.compareToIgnoreCase(l));
	}

}
