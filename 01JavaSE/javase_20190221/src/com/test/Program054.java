/*
 * 프로그램명 : 배열(저장소)을 이용한 성적처리
 * 작성자 : 이민종
 * 작성일 : 20190221
 * 프로그램기능 : 배열 설명
 */

package com.test;

public class Program054 {

	public static void main(String[] args) {
		//과목1, 과목2, 과목3의 점수를 저장하는 배열 저장소 준비
		//각 과목당 인원수는 5명
		//각 과목당 총점, 평균 계산

		
		//입력 과정 ----------
		//과목1에 대한 점수
		int[] java = {100, 80, 90, 50, 60,70,80,78,89,45, 0, 0};
	    int[] c = {80, 90, 60, 55, 44, 44,60,70,80,78, 0, 0};
	    int[] html = {23, 20, 60, 55, 89, 44,55, 44, 44,60, 0, 0};
		
		//처리 과정 ----------
		int len = java.length;
		
		//총점 계산
		int total = 0;
		int total2 = 0;
		int total3 = 0;
		
		int ave = 0;
		int ave2 = 0;
		int ave3 = 0;
		
		for (int a = 0; a < (len - 2); ++a) {
			//누적 연산
			total += java[a];
			total2 += c[a];
			total3 += html[a];
		}
		java[len-2] = total;
		c[len-2] = total2;
		html[len-2] = total3;
		
		//평균 계산
		//정수 나눗셈의 단점 
		//- 소수 이하를 버린다(절삭)
		//-> 반올림 액션 추가
				
		ave = Math.round(total / (float)(len - 2));
		ave2 = Math.round(total2 / (float)(len - 2));
		ave3 = Math.round(total3 / (float)(len - 2));
		java[len-1] = ave;
		c[len-1] = ave2;
		html[len-1] = ave3;
		
		
		//출력 과정 ----------
		//점수, 총점, 평균 일괄 출력
		for (int a = 0; a < (len - 2); ++a) {
			System.out.printf("%2d) %5d %5d %5d%n",(a+1), java[a], c[a], html[a]);
		}
		System.out.println("--------------------------");
		for (int a = (len - 2); a < len; ++a) {
			System.out.printf("     %5d %5d %5d%n", java[a], c[a], html[a]);
		}
	}

}
