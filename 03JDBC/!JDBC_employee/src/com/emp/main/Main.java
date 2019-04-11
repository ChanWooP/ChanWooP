package com.emp.main;

import java.util.Scanner;

import com.emp.service.LoginService;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			
			System.out.println();
			System.out.println("**직원관리 v2.0**");
			System.out.println("1.로그인");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) break;
			
			switch (m) {
			case 1:	new LoginService().login(sc); break;
			}
			
		}		
			
		sc.close();
		System.out.println("프로그램 종료");
	}

}
