/*
 * 프로그램명 : 변수
 * 작성자 : 박찬우
 * 작성일 : 20190219
 * 프로그램기능 : 변수 설명2
 */

package com.pcw;

public class Program014 {

	public static void main(String[] args) {

		//동일 자료형의 변수들은 같이 선언 가능
		//변수 선언과 초기값 할당을 같이 선언 가능
		//int 자료형은 기본 초기값 0 
		//double 자료형은 기본 초기값 0.0 
		int kor = 60, eng = 70, math = 80;
		int total = 0;
		double avg = 0.0;
		
		total = kor + eng + math;
		avg = total / 3;
		
		System.out.printf("총점:%d, 평균%f", total, avg);
	}

}
