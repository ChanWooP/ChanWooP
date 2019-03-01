/*
 * 프로그램명 : 석차 알고리즘
 * 작성자 : 박찬우
 * 작성일 : 20190222
 * 프로그램기능 : 석차 알고리즘 설명
 */


package com.test;

import java.util.*;// 배열 초기화를 위한 api

public class Program058 {

	public static void main(String[] args) {
		//석차 알고리즘
		
		//입력과정
		//과목 1에 대한 점수
		int[] java = {1,34,2,25,78,78,100};
		//int[] rank = {1, 1, 1, 1, 1, 1, 1};
		int[] rank = new int[java.length];
		Arrays.fill(rank, 1); // 배열 초기화
		int len = java.length;
		
		//처리과정
		//석차 계산
		for(int a=0; a<len; ++a) {
			//나머지 성적들
			for(int b=0; b<len; ++b) {
				//비교 대상이 자기 자신인 경우는 continue
				if(a == b) continue;// 해당 조건이 맞으면 다음 for문 실행
				//상대방 점수가 높으면
				//나의 석차+1 연산
				if(java[a] < java[b]) {
					++rank[a];
				}
			}
			
		}
			
		for (int a = 0; a < len; ++a) {
			System.out.printf("%2d) %5d (%2d등 )%n", (a+1), java[a], rank[a]);
		}
	}

}
