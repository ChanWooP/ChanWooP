package com.test061;

public class Sample {
	private int a;
	
	public Sample() {
		
	}
	
	public Sample(int a) {
		this.a = a;
	}

	@Override
	public String toString() {
		return String.format("부모클래스");
	}
	
	public void method() {
		System.out.println(a);
	}
}
