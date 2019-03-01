/*
 * 프로그램명 : 배열
 * 작성자 : 박찬우
 * 작성일 : 20190221
 * 프로그램기능 : 배열 설명
 */

package com.test;

public class Program053 {

	public static void main(String[] args) {
		//특정 값 탐색
		int[] a = {1,3,2};
		int temp = 0;
		
		for(int b=0;b<a.length;++b) {
			temp = a[b];
			if(temp==3) {
				System.out.println(temp);
			}
		}
		
		//정렬)오름차순 또는 내림차순) 출력
		java.util.Arrays.sort(a);
		
		for(int b=0;b<a.length;++b) {
			System.out.printf("%d",a[b]);
		}
		System.out.println();
	}

}
