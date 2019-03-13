package com.emp001.service;

import java.util.Scanner;

public class SubService {
	
	public void menu(Scanner sc) {
		while(true) {
			System.out.println("직원관리v1.0 / 2.기초정보관리");
			System.out.println("-----------------------------");
			System.out.println("1.지역관리 2.부서관리 3.직위관리");
			System.out.print("선택(1~3, 0 quit)>");
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 0) break;
			
			switch(choice) {
			case 1: new RegionService().menu(sc); break;
			case 2:	break;
			case 3: break;
			}
		}
	}
}
