package com.schedule;

import java.util.Scanner;

public class ScheduleService {
	
	private ScheduleDAO dao = new ScheduleDAO();
	
	public void menu01(Scanner sc) {
		
	}

	public void menu02(Scanner sc) {
		
		while(true) {
			System.out.println("2.일정 출력 및 검색");
			System.out.println("1.오늘일정 2.특정일일정 3.특정월일정 4.특정단어포함일정 5.전체일정");
			System.out.print("선택>");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 0) break;
			
			switch(choice) {
			case 1: menu02_1(sc); break;
			case 2: menu02_2(sc); break;
			case 3: menu02_3(sc); break;
			case 4: menu02_4(sc); break;
			case 5: menu02_5(sc); break;
			}
		}
	}
	
	public void menu02_1(Scanner sc) {
		System.out.println("2.일정 출력 및 검색 / 1.오늘일정");
		System.out.println("--------------------------------");
	}
	
	public void menu02_2(Scanner sc) {
		System.out.println("2.일정 출력 및 검색 / 2.특정일일정");
		System.out.println("--------------------------------");
	}
	
	public void menu02_3(Scanner sc) {
		System.out.println("2.일정 출력 및 검색 / 3.특정월일정");
		System.out.println("--------------------------------");
	}
	
	public void menu02_4(Scanner sc) {
		System.out.println("2.일정 출력 및 검색 / 4.특정단어포함일정");
		System.out.println("--------------------------------");
	}
	
	public void menu02_5(Scanner sc) {
		System.out.println("2.일정 출력 및 검색 / 5.전체일정");
		System.out.println("--------------------------------");
	}
	
	public void print_(Schedule[] list) {
		
	}

}
