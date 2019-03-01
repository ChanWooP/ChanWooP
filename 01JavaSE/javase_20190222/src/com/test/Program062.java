/*
 * 프로그램명 : 2차원 배열
 * 작성자 : 박찬우
 * 작성일 : 20190222
 * 프로그램 기능 : 2차원 배열 설명
 */

package com.test;

public class Program062 {

	public static void main(String[] args) {
		/*
		1  2  3  4  5 
		6  7  8  9 10 
		11 12 13 14 15 
		16 17 18 19 20 
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
			for(int b=0;b<arr[a].length; ++b) {
				arr[a][b] = ++temp;
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
