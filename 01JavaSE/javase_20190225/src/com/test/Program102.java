package com.test;

import java.io.IOException;

public class Program102 {

	public static void main(String[] args) throws IOException {
		//단어, 문장 단위로 입력 처리
		java.util.Scanner sc = new java.util.Scanner(System.in);
		
		//next() 메소드는 단어 단위로 입력 처리 가능
		//주의) 자료를 연속 입력시 Enter키가 버퍼에 남아있는 상태가 된다
		//test, Enter
		String a = sc.next();  
		String b = sc.next();
		System.out.println(a); //test
		System.out.println(b); //test1
		System.out.printf("%s%n", a);  //test

		//Scanner 객체 사용에 대한 마무리
		sc.close();
	}

}
