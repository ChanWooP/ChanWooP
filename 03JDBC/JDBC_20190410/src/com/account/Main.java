package com.account;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AccountService serviceS = new AccountService();
		while(true) {
			System.out.println("--계좌관리--");
			System.out.println("1.계좌조회  2.계좌입금 3.계좌출금 4.종료");
			System.out.print("선택>");
			int c = sc.nextInt();
			sc.nextLine();
			
			if(c == 4) break;
			
			switch(c) {
			case 1: serviceS.menu01(sc); break;
			case 2: serviceS.menu02(sc); break;
			case 3: serviceS.menu03(sc); break;
			case 99: serviceS.adminCheck(sc); break;
			}
		}
		sc.close();
		System.out.println("프로그램 종료");
	}

}
