/*
 * 프로그램명 : 홀짝확인
 * 작성자 : 박찬우
 * 작성일 : 20190220
 * 프로그램기능 : 홀짝확인하기
 */

package com.pcw;

public class Program031 {

	public static void main(String[] args) {
		  //조건식의 결과가 true인 경우만 중괄호({}) 안에 있는 실행문 실행 가능
		  //조건식 - 비교, 논리 연산자를 이용한 수식
		  /*
		  if(Boolean_expression) {
		     // Statements will execute if the Boolean expression is true
		  } 
		  */
		  
		  
		  //문제) 특정 숫자가 홀수인지, 짝수인지 판별해서 메시지 출력
		  //출력예)
		  //숫자: 9
		  //결과: 홀수
		
		int number = 1025;
		String name = "홀수";
		
		if(number%2 == 0)
		{
			name = "짝수";
		}
		
		System.out.printf("%d%n", number);
		System.out.printf("%s%n", name);
	}

}
