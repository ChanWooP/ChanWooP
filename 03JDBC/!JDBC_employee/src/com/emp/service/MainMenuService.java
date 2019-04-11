package com.emp.service;

import java.util.Scanner;

import com.emp.persistance.LoginDAO;

public class MainMenuService {
	
	public void mainMenu(Scanner sc, String id) {
		
		while(true) {
			System.out.println(">>직원관리 v2.0");
			System.out.println("1.직원관리  2.기초정보관리  3.관리자정보관리");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();
			if (m == 0) break;
			switch (m) {
			case 1: menu1(sc);	break;
			case 2: menu2(sc);	break;
			case 3: menu3(sc,id);	break;
			}
		}
	}

	private void menu1(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private void menu2(Scanner sc) {
		while(true) {
			System.out.println(">>직원관리 v2.0 > 2.기초정보관리");
			System.out.println("1.지역관리  2.부서관리  3.직위관리");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();
			if (m == 0) break;
			switch (m) {
			case 1: menu21(sc);	break;
			case 2: menu22(sc);	break;
			case 3: menu23(sc);	break;
			}
		}		
	}
	
	private void menu21(Scanner sc) {
		new RegionService().menu(sc);
	}

	private void menu22(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private void menu23(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private void menu3(Scanner sc, String id) {
		while(true) {
			System.out.println(">>직원관리 v2.0 > 3.관리자정보관리");
			System.out.println("1.패스워드변경");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();
			if (m == 0) break;
			switch (m) {
			case 1: menu31(sc, id);	break;
			}
		}
	}
	
	private void menu31(Scanner sc, String id) {
		System.out.print("현재패스워드>");
		String oldPw = sc.nextLine();
		System.out.print("신규패스워드>");
		String newPw = sc.nextLine();
		System.out.print("패스워드를 변경할까요(0/1)>");
		int m = sc.nextInt();
		sc.nextLine();

		if(m == 1) {
			int result = new LoginDAO().pwChange(id, oldPw, newPw);
			if(result != 0) {
				System.out.printf("관리자'%s'의 패스워드가 변경되었습니다.",id);
			}else {
				System.out.printf("관리자'%s'의 패스워드가 변경되어지지 않았습니다.",id);
			}
			
		}else {
			System.out.printf("관리자'%s'의 패스워드 변경이 취소되었습니다.",id);
		}
	}
}
