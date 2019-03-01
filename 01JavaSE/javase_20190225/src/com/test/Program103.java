package com.test;

import java.io.IOException;

public class Program103 {

	public static void main(String[] args) throws IOException {
		//단어, 문장 단위로 입력 처리
		java.util.Scanner sc = new java.util.Scanner(System.in);

		//문장 전체를 하나의 자료를 입력하는 과정
		//Java runs on a variety of platforms, Enter
		String a = sc.nextLine();  
		System.out.println(a); //Java runs on a variety of platforms

		//Scanner 객체 사용에 대한 마무리
		sc.close();
	}

}
