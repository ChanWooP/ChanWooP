package com.emp001.main;

import java.util.Scanner;
import com.emp001.service.SubService;;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SubService subMenu = new SubService();
		
		while(true) {
			System.out.println("직원 관리v1.0");
			System.out.println("-------------");
			System.out.println("1.직원관리 2.기초정보관리");
			System.out.print("선택(1~2, 0 quit)>");
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 0) break;
			
			switch(choice) {
			case 1: break;
			case 2: subMenu.menu(sc); break;
			}
		}
		System.out.println("프로그램 종료!");
		sc.close();
	}

}
