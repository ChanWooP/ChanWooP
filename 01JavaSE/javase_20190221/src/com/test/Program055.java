/*
 * 프로그램명 : 배열(저장소)을 숫자, 문자열 정렬
 * 작성자 : 박찬우
 * 작성일 : 20190221
 * 
 */

package com.test;

public class Program055 {

	public static void main(String[] args) {
		//정렬(Sort)
		//오름차순, 내림차순
		//-> java.util.Arrays.sort() 메소드
		//-> 정렬 알고리즘 구현
		//-> 석차 알고리즘 구현
		
		//거품정렬
		//-> 인접한 두 요소 비교 후, 큰 숫자를 오른쪽으로 이동
		//-> 1회전시 가장 큰 숫자가 가장 오른쪽에 저장
		//-> n회전시 모든 숫자가 정렬된다. n은 요소 전체 갯수
		int arr[] = {67,53,11,4,25,3,12,44,13};
		int temp = 0;
		
		
		//원본상태 출력
		System.out.println("원본 :" + java.util.Arrays.toString(arr));
		
		// 첫번째와 두번째 숫자 비교
		if(arr[0] > arr[1]) {
			temp = arr[0];
			arr[0] = arr[1];
			arr[1] = temp;
		}
		System.out.println("1번비교 : " + java.util.Arrays.toString(arr));
		
		// 1회전
		for(int b=0; b<arr.length-1; ++b) {
			
			if(arr[b] > arr[b+1]) {
				temp = arr[b];
				arr[b] = arr[b+1];
				arr[b+1] = temp;
			}
		}
		System.out.println("1회전 : " + java.util.Arrays.toString(arr));
		
		//n회전
		int len = arr.length-1;//0번쨰 부터 마지막배열 개수로 맞추기
		
		for(int a=0; a<len; ++a) {// 배열의 모든 값 비교하기 위함
			for(int b=0; b<len-a; ++b) {//맨뒤로 간 숫자는 더이상 계산하지 않도록
				if(arr[b] > arr[b+1]) {
					temp = arr[b];
					arr[b] = arr[b+1];
					arr[b+1] = temp;
				}
			}
			
		}
		System.out.println("n회전 : " + java.util.Arrays.toString(arr));	
	}

}
