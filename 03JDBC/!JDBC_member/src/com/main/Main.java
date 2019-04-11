package com.main;

import java.util.Scanner;

import com.memberService.MemberService;

public class Main {

	public static void main(String[] args) {
		MemberService servicem = new MemberService();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("---회원 정보 관리---");
			System.out.println("1.회원정보출력 2.회원정보입력");
			System.out.print("선택>");
			int c = sc.nextInt();
			sc.nextLine();
			
			if(c==0) break;
			
			switch(c) {
			case 1: servicem.menu01(); break;
			case 2: servicem.menu02(sc); break;
			}
		}
		sc.close();
		System.out.println("프로그램 종료");
	}

}
