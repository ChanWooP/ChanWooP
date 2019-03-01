package com.test;

public class Program064 {

	public static void main(String[] args) {
		/*
		 1  2  3  4  5 
		10  9  8  7  6 
		11 12 13 14 15 
		20 19 18 17 16 
		21 22 23 24 25 

		 */
		//행과 열의 크기가 동일한 배열
		//예를 들어 3x3, 4x4
		
		//2차원 배열 선언
		int[][] arr = new int[5][5];
		
		//2차원 배열 자료 채우기
		// 특정범위(1~25) 순서대로 채우기
		int temp = 0;
		
		
		//행우선 채우기
		for(int a=0;a<arr.length;++a) {
			//열 접근 법
			int len = arr.length;
			for(int b=0;b<len; ++b) {
				if(a%2 == 0) {
					arr[a][b] = ++temp;
				}else {
					arr[a][len-1-b] = ++temp;
				}
				
			}

		}
		
		//2차원 배열 요소 전체 출력
		//행 접근 법
		for(int a=0;a<arr.length;++a) {
			//열 접근 법
			for(int b=0;b<arr[a].length; ++b) {
				System.out.printf("%2d ", arr[a][b]); 
			}
			System.out.println();
		}
	}

}
