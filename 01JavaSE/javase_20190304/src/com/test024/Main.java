package com.test024;

public class Main {

	public static void main(String[] args) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		Service s = new Service();
		while(true) {
			System.out.println("---성적 처리---");
			System.out.println("1.성적정보출력 2.성적정보입력");
			System.out.print("선택>");
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 0) break;
			
			switch(choice) {
			case 1: s.menu01(sc); break;
			case 2: s.menu02(sc); break;
			}
		}
		
		System.out.println("프로그램 종료");
		sc.close();
	}

}
