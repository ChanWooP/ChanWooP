package com.test;


public class Program072 {

	public static void main(String[] args) {
		//석차 알고리즘
		
		//입력과정
		//2차원 배열 사용
		//과목과 석차를 2차원 배열로 처리
		//{{과목1,석차1},{과목2,석차2}, ...}
		
		//과목1에 대한 점수 및 석차 저장
		int[][] javas = {{100,1},{90,1},{80,1}
						,{70,1}, {60,1}, {50,1}
						,{30,1}, {40,1}, {30,1}, {10,1}};
		
		int len = javas.length;
		int temp = 0; // 같은 점수일 시 비교하지 않기
		
		//석차 계산 과정
		for(int a=0; a<len; ++a) {
			for(int b=0;b<len; ++b) {
				if(javas[a][0] < javas[b][0]) {
					++javas[a][1]; 
				}
			}
		}
		
		//출력
		for(int a=0;a<len;++a) {
			System.out.printf("%2d) %3d점 %d등 ", ++temp,javas[a][0],javas[a][1]);
			System.out.println();
		}
	}

}
