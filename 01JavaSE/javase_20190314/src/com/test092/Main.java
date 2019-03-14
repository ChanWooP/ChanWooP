package com.test092;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//메인메뉴
		//1.자동번호 2.수동번호 3.당첨자검색
		Scanner sc = new Scanner(System.in);
		LottoService service = new LottoService();
		
		while(true) {
			System.out.println("로또");
			System.out.println("1.자동번호 2.수동번호 3.당첨자검색");
			System.out.println("----------------------------------");
			System.out.print("선택>");
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 0) break;
			
			switch(choice) {
			case 1: service.menu1(sc); break;
			case 2: service.menu2(sc); break;
			case 3: service.menu3(sc); break;
			}
			
		}
		
	}

}
