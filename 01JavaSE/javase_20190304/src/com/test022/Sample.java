package com.test022;

public class Sample {
	// 문제) 십진수 숫자를 전달하면, 2진수 문자열을 반환하는 메소드
	// 출력예
	// 1-> 1
	// 2-> 10
	// 3-> 11
	public String change(int n) {
		String s = "";
		while (true) {
			if (n == 0)break;
			
			s = (n % 2) + s;
			n = n / 2;
		}
		return s;

	}
}
