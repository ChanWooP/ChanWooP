package com.test;

public class Program056 {

	public static void main(String[] args) {
		//n회전. n은 요소 전체 갯수 - 1
				//주의) 치환이 일어나지 않는 상태가 되면 더 이상 정렬할 필요가 없다
		int[] arr = {1,34,2,25,78,22,124};
		int len = arr.length-1;//0번쨰 부터 마지막배열 개수로 맞추기
		int temp = 0;
		
		for(int a=0; a<len; ++a) {// 배열의 모든 값 비교하기 위함
			for(int b=0; b<len-a; ++b) {//맨뒤로 간 숫자는 더이상 계산하지 않도록
				if(arr[b] > arr[b+1]) {
					temp = arr[b];
					arr[b] = arr[b+1];
					arr[b+1] = temp;
				}
			}
			System.out.println(a+"회전 : " + java.util.Arrays.toString(arr));
			
		}
		System.out.println("n회전 : " + java.util.Arrays.toString(arr));
	}

}
