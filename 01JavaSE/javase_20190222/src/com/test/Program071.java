/*
 * 프로그램명 : 배열 복사
 * 작성자 : 박찬우
 * 작성일 : 20190222
 * 프로그램기능 : 깊은복사와 얕은복사 구분
 */

package com.test;

public class Program071 {

	public static void main(String[] args) {
		//Arrays 클래스의 sort() 메소드는
		//외부에서 전달된 배열의 참조주소만 받아들여서
		//원본 배열의 요소를 정렬한다.
		
		int[] arr = {5,4,3,2,1};
		
		//원본출력
		System.out.println(java.util.Arrays.toString(arr));
		
		// 참조주소를 보내줌
		// 참조주소가 가리키는 배열 요스를 정렬
		java.util.Arrays.sort(arr);
		
		//정렬 후 상태 출력
		System.out.println(java.util.Arrays.toString(arr));
	}

}
