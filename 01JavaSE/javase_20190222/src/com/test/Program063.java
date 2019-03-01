package com.test;

public class Program063 {

	public static void main(String[] args) {
		/*
		1  6 11 16 21 
		2  7 12 17 22 
		3  8 13 18 23 
		4  9 14 19 24 
		5 10 15 20 25 

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
			for(int b=0;b<arr[a].length; ++b) {
				arr[b][a] = ++temp;
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
