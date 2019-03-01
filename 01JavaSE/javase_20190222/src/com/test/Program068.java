/*
 * 프로그램명 : 1차원 -> 2차원
 * 작성자 : 박찬우
 * 작성일 : 20190222
 * 프로그램기능 : 1차원배열을 2차원배열로 변환
 */

package com.test;

public class Program068 {

	public static void main(String[] args) {
		//1차원 -> 2차원
		
		//1차원 배열 선언
		int[] arr = new int[25]; // 1차원 배열 선언
		int len = arr.length; // 1차원 배열의 크기
		
		//1차원 배열 데이터 채우기
		for(int a = 0; a<len; ++a) {
			arr[a] = (int)(Math.random()*25)+1;
		}
		
		//1차원 배열 출력
		System.out.println(java.util.Arrays.toString(arr));
		
		//-------------------------------------------------
		
		//2차원 배열 선언
		int count = 5;
		int row = arr.length/count; // 2차원 배열을 위한 나누기
		int[][] arr2 = new int[row][]; // 2차원 배열 선언
		int c = 0; // arr 인덱스를 위한 변수
		
		//2차원 배열 데이터 채우기
		for(int a=0; a<row; ++a) {
			arr2[a] = new int[count];
			for(int b=0;b<count;++b) {
				arr2[a][b] = arr[c++];
			}
		}
		
		//2차원 배열 출력
		for(int a=0;a<arr2.length;++a) {
			for(int b=0;b<arr2[a].length; ++b) {
				System.out.printf("%2d ", arr2[a][b]); 
			}
			System.out.println();
		}
	}

}
