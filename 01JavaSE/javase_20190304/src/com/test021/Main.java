package com.test021;

public class Main {

	public static void main(String[] args) {
		Sample s = new Sample();
		
		System.out.printf("%d%n",s.plus(100, 6));
		System.out.printf("%d%n",s.minus(100, 6));
		System.out.printf("%d%n",s.multiply(100, 6));
		System.out.printf("%.2f",s.division(100, 6));
	}

}
