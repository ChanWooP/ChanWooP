package com.test031;

public class Sample {
	
	public void ref(int[] a) {
		a[0] = a[0] + 1;
	}
	
	public int var(int a) {
		a = a + 1;
		return a;
	}
}
