package com.test022;

public class Sample {
	// 문제) 십진수 숫자를 전달하면, 2진수 문자열을 반환하는 메소드
	// 출력예
	// 1-> 1
	// 2-> 10
	// 3-> 11
	public String change2(int n) {
		String s = "";
		while (true) {
			if (n == 0)break;
			
			s = (n % 2) + s;
			n = n / 2;
		}
		return s;
	}
	
	public String change16(int n) {
		String s = "";
		String[] array = {"0","1","2","3","4","5","6","7",
				"8","9","A","B","C","D","E","F"};
		while(true) {
			
			
			s = array[(n % 16)] + s;
			
			n = n / 16;
			
			if(n == 0) break;
		}
		return s;
	}
}
