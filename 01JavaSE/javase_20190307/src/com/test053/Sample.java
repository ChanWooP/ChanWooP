package com.test053;

public class Sample {
	private static int a = 1;
	private int b = 2;
	
	public void method1() {
		System.out.print(a);
		System.out.print(b);
		System.out.println();
	}
	
	public static void method2() {
		System.out.print(a);
		//System.out.println(b); - 오류
	}
	
	public void setA(int a) {
		this.a = a;
	}
	
	public void setB(int b) {
		this.b = b;
	}
	
}
