package com.test031;

public class Main {

	public static void main(String[] args) {
		Sample s = new Sample();
		int[] a = {1,2};
		
		// by value
		System.out.println(s.var(10)); // 값만 넘기기 때문에 리턴 필요
		
		// by reference
		s.ref(a); // 참조형이기 때문에 리턴이 필요 없음
		
		for(int b=0; b<a.length; ++b) {
			System.out.printf("%d ",a[b]);
		}
		
		int[] arr = {1, 4, 5 ,2, 3};
		java.util.Arrays.sort(arr);
		System.out.println(java.util.Arrays.toString(arr));
		
	}

}
