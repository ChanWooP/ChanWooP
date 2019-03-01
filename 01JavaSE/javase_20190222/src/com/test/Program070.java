/*
 * 프로그램명 : 배열 복사
 * 작성자 : 박찬우
 * 작성일 : 20190222
 * 프로그램기능 : 깊은복사와 얕은복사 구분
 */

package com.test;

public class Program070 {

	public static void main(String[] args) {
		//배열 요소 복사
		
		//1차원 배열 -> 깊은 복사(자료가 복사된 상태)
		int[] arr = {1, 2, 3, 4, 5}; // arr 선언
		System.out.println(java.util.Arrays.toString(arr));// arr 출력
		
		int[] arr2 = java.util.Arrays.copyOf(arr, arr.length);// arr을 복사해서 arr2에 붙여넣는다
		System.out.println(java.util.Arrays.toString(arr2));//arr2 출력
		System.out.println();
		
		arr[0] = 10;// arr 값 변경
		System.out.println(java.util.Arrays.toString(arr)); // arr 출력
		System.out.println(java.util.Arrays.toString(arr2)); // arr2 출력
		//결과 : 서로 다른 값을 가짐
		
		
		//2차원 배열 -> 얕은 복사(참조주소가 복사된 상태)
		int[][] arr3 = {{1,2,3},{1,2}}; // arr3 선언
		int[][] arr4 = java.util.Arrays.copyOf(arr3, arr3.length);// arr4 선언
		System.out.println();
		
		//arr3 출력
		for(int a=0;a<arr4.length;++a) {
			for(int b=0;b<arr4[a].length; ++b) {
				System.out.printf("%2d ", arr4[a][b]); 
			}
			System.out.println();
		}
		
		//arr4 출력
		for(int a=0;a<arr4.length;++a) {
			for(int b=0;b<arr4[a].length; ++b) {
				System.out.printf("%2d ", arr4[a][b]); 
			}
			System.out.println();
		}
		System.out.println();
		
		//arr3 값 변경
		arr3[1][1] = 1;
		
		//arr3 출력
		for(int a=0;a<arr4.length;++a) {
			for(int b=0;b<arr4[a].length; ++b) {
				System.out.printf("%2d ", arr4[a][b]); 
			}
			System.out.println();
		}
		
		//arr4 출력
		for(int a=0;a<arr4.length;++a) {
			for(int b=0;b<arr4[a].length; ++b) {
				System.out.printf("%2d ", arr4[a][b]); 
			}
			System.out.println();
		}
		//결과 : 서로 값이 동일함
	}

}
