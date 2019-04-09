package com.score;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ScoreService serviceS = new ScoreService();
		
		while(true) {
			System.out.println("-----성적관리-----");
			System.out.println("1.성적정보입력 2.성적정보출력 3.성적정보검색");
			System.out.print("선택>");
			int c = sc.nextInt();
			sc.nextLine();
			
			if(c==0) break;
			
			switch(c)
			{
			case 1: serviceS.menu01(sc); break;
			case 2:	serviceS.menu02(); break;
			case 3:	serviceS.menu03(sc); break;
			}
		}
		sc.close();
		System.out.println("프로그램 종료");
	}

}
