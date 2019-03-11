package com.test061;

public class Sub extends Sample{
	
	public Sub() {
		
	}
	
	public Sub(int a) {
		super(a);
	}
	
	@Override
	public String toString() {
		return String.format("자식클래스");
	}

	@Override
	public void method() {
		super.method();
		System.out.println("자식");
	}
	
}
