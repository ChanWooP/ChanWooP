/*
 * 프로그램명 : 2차원 -> 1차원
 * 작성자 : 박찬우
 * 작성일 : 20190222
 * 프로그램기능 : 2차원배열을 1차원배열로 변환
 */

package com.test;

public class Program067 {

	public static void main(String[] args) {
		//1차원 배열 -> 2차원 배열 변환
		//랜덤한 크기(5x5)의 2차원 배열 요소 (1부터 순차적으로)를
		
		int[][] arr = new int[5][5]; // 2차원 배열
		int temp = 0; // 증가변수
		int len = arr.length; // arr의 크기
		
		//2차원 배열 데이터 채우기
		for(int a=0;a<arr.length;++a) {
			for(int b=0;b<arr[a].length; ++b) {
				arr[a][b] = ++temp;
			}
		}
		
		//2차원 배열 요소 전체 출력
		for(int a=0;a<arr.length;++a) {
			for(int b=0;b<arr[a].length; ++b) {
				System.out.printf("%2d ", arr[a][b]); 
			}
			System.out.println();
		}
		
		//1차원 배열로 변환
		//->배열은 선언시 크기 지정
		int[] arr2 = new int[len * len]; // 1차원 배열 선언
		int c = 0;// 인덱스 변수
		
		//1차원 배열 데이터 채우기
		for(int a=0;a<arr.length;++a) {
			for(int b=0;b<arr[a].length; ++b) {
				arr2[c++] = arr[a][b];
			}
		}
		
		//1차원 배열 출력
		System.out.println(java.util.Arrays.toString(arr2));

	}

}
