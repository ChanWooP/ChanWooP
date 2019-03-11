package com.schedule2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ScheduleService {
	
	private ScheduleDAO dao = new ScheduleDAO();
	private SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	private Date date = new Date();
	
	public void menu01(Scanner sc) {
		System.out.println("1.일정 입력");
		System.out.print("날짜(YYYY-MM-DD)>");
		String date = sc.nextLine();
		System.out.print("내용(200자이내)?");
		String content = sc.nextLine();
		System.out.print("정말 입력하시겠습니까(0/1)>");
		int choice = sc.nextInt();
		
		if(choice == 1) {
			Schedule schedule = new Schedule(date, content);
			dao.add(schedule);
			
			System.out.println("입력이 완료되었습니다");
		}else {
			System.out.println("입력이 취소되었습니다");
		}
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
		String key = "today";
		String value = format1.format(date);
		Schedule[] list = this.dao.list(key, value);
		
		System.out.println("2.일정 출력 및 검색 / 1.오늘일정");
		print_(list);
	}
	
	public void menu02_2(Scanner sc) {
		String key = "getday";
		System.out.print("특정일(yyyy-MM-dd)>");
		String value = sc.nextLine();
		Schedule[] list = this.dao.list(key, value);
		
		System.out.println("2.일정 출력 및 검색 / 2.특정일일정");
		print_(list);
	}
	
	public void menu02_3(Scanner sc) {
		String key = "getmonth";
		System.out.print("특정월(yyyy-MM)>");
		String value = sc.nextLine();
		Schedule[] list = this.dao.list(key, value);
		
		System.out.println("2.일정 출력 및 검색 / 3.특정월일정");
		print_(list);
	}
	
	public void menu02_4(Scanner sc) {
		String key = "getdan";
		System.out.print("특정단어>");
		String value = sc.nextLine();
		Schedule[] list = this.dao.list(key, value);
		
		System.out.println("2.일정 출력 및 검색 / 4.특정단어포함일정");
		print_(list);
	}
	
	public void menu02_5(Scanner sc) {
		String key = "all";
		String value = null;
		Schedule[] list = this.dao.list(key, value);
		
		System.out.println("2.일정 출력 및 검색 / 5.전체일정");
		print_(list);
	}
	
	public void print_(Schedule[] list) { 
		int count = 0;
		
		System.out.println("--------------------------------");
		System.out.println("번호 / 날짜 / 내용");
		for(Schedule s : list) {
			if(s != null) {
				System.out.println(s.toString());
				++count;
			}
		}
		System.out.printf("총 %d건%n",count);
	}

}
