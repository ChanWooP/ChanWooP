package com.test054;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ScoreService service = new ScoreService();
		
		while(true) {
			System.out.println("**성적관리 v1.0**");
			System.out.println("1.성적정보입력 2.성적정보출력 3.성적정보검색");
			System.out.print("선택>");
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 0) break;
			
			switch(choice) {
			case 1: service.menu01(sc); break;
			case 2: service.menu02(sc); break;
			case 3: service.menu03(sc); break;
			}
		}
		System.out.println("프로그램 종료!");
		sc.close();
	}

}
