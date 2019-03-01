package com.test;

public class Program057 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//n회전
		int[] arr = {1,34,2,25,78,22,124};
		int len = arr.length - 1;
		for (int a = 0; a < len; ++a) {
			boolean swap = false;
			for (int b = 0; b < len - a; ++b) {
				if (arr[b] > arr[b + 1]) {
					int temp = arr[b + 1];
					arr[b + 1] = arr[b];
					arr[b] = temp;
					swap = true;
				}
			}
			System.out.println(a+": "+java.util.Arrays.toString(arr)); //중간결과 보기
			if (!swap) break;
		}
		System.out.println(java.util.Arrays.toString(arr));
	}

}
