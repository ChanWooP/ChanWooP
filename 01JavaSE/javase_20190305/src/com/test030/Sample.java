package com.test030;

public class Sample {
	
	public void method() {
		System.out.println("hello world");
	}
	
	public void method(int a) {
		System.out.printf("%d%n", a);
	}
	
	public void method(double a) {
		System.out.printf("%.1f%n", a);
	}
	
	public void method(double a, double b) {
		System.out.printf("%.1f/%.1f%n", a, b);
	}
}
