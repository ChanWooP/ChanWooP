package com.pcw;

public class Program034 {

	public static void main(String[] args) {
		//문제) 생년월일을 가지고 년도를 가지고, 특정 
		//1980 1990 2000
		//출력 예)
		//생년월일(연도) : 1985
		//메시지 : 80년대생
		
		int a = 2019;
		String b = null;
		
		if(a >= 2010) {
			b = "2010";
		}else if(a >= 2000) {
			b = "2000";
		}else if(a >= 1990){
			b = "1990";
		}else {
			b = "1980";
		}
		
		System.out.printf("%s년대생", b);
		
	}

}
