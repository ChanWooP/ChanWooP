package com.test074;

public class Main {

	public static void main(String[] args) {
		
		Sample3 s3;
		
		Sample1 s1 = new Sample1();
		Sample2 s2 = new Sample2();
		
		
		s3 = new Sample3(s1);
		s3.method();
		
		//s3 = new Sample3(s2); 에러
		//s3.method();


	}

}
