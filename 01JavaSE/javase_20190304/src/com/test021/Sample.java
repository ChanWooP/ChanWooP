package com.test021;

public class Sample {
	//문제) 정수 숫자 2개를 전달하고,
	//산술적 연산 수행 후, 결과 반환하는 메소드
	//더하기, 빼기, 곱하기, 나누기(실수 연산)
	public int plus(int a, int b) {
		return a+b;
	}
	
	public int minus(int a, int b) {
		return a-b;
	}
	
	public int multiply(int a, int b) {
		return a*b;
	}
	
	public double division(int a, int b) {
		return (double)a/(double)b;
	}
}
