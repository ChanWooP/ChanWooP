/*
 * 프로그램명 : 배열
 * 작성자 : 박찬우
 * 작성일 : 20190221
 * 프로그램기능 : 배열 설명
 */

package com.test;

public class Program052 {

	public static void main(String[] args) {
		//배열(Array)
		//여러개의 자료를 하나의 저장소에 저장할 수 있는 수단
		int[] arr = {1,2,3};
		int sum = 0;
		
		System.out.printf("%d%d%d%n", arr[0],arr[1],arr[2]);
		sum = arr[0]+arr[1]+arr[2];
		System.out.printf("%d%n",sum);
		sum = 0;
		
		
		for(int a=0; a<arr.length; ++a) {
			sum = sum + arr[a];
		}
		System.out.printf("%d",sum);
		
		
	}

}
