package com.test;

import java.io.IOException;

public class Program104 {

	public static void main(String[] args) throws IOException {
		
		java.util.Scanner sc = new java.util.Scanner(System.in);

		while(true) {
			System.out.print("숫자입력:");
			int a = sc.nextInt(); // 엔터키 전까지의 숫자만 입력 받음
			sc.nextLine(); // 개행문자 삭제
			if (a == 0) break;
			System.out.println(a);
		}

		//Scanner 객체 사용에 대한 마무리
		sc.close();

	}

}
