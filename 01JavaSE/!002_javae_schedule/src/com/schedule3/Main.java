package com.schedule3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		ScheduleService service = new ScheduleService();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("**일정관리 v1.0**");
			System.out.println("1.일정 입력 2.일정 출력 및 검색");
			System.out.print("선택>");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 0) break;
			
			switch(choice) {
			case 1: service.menu01(sc); break;
			case 2: service.menu02(sc); break;
			}
		}
		System.out.println("프로그램 종료!!");
		sc.close();
	}



}
