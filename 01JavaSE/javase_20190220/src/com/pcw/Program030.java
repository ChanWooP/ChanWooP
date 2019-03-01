/*
 * 프로그램명 : 제어구조-if문
 * 작성자 : 박찬우
 * 작성일 : 20190220
 */

package com.pcw;

public class Program030 {

	public static void main(String[] args) {
		//조건식의 결과가 true인 경우만 중괄호({}) 안엥 있다
		//조건식 - 비교, 논리 연산자를 이용한 수식
		/*
			if(Boolean_expression) {
			   // Statements will execute if the Boolean expression is true
			}
		 */
		//예를 들어 특정조건(점수가 60점 이상일 경우) 만족 시 '합격'
		int a = 10;
		
		//메시지 저장용 String 자료형 변수
		//문자열은 쌍따옴표(")로 구성된 여러개의 문자 집합
		String b = "불합격";
		
		if(a >= 60) {
			b = "합격";
		}
		
		System.out.printf("점수 : %d%n", a);
		System.out.printf("결과 : %s%n", b);
		//----------------------------------------
		int x = 10;
		String x2 = "";
		
		if( x< 20) {
			x2 = "This is if statment";
		}
		System.out.printf("%s%n",x2);
		
		
	}

}
