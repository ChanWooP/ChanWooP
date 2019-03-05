package com.test033;

public class Main {

	public static void main(String[] args) {
		MemberService service = new MemberService();
		
		java.util.Scanner sc = new java.util.Scanner(System.in);
	
		while(true) {
			System.out.println("---회원 관리;---");
			System.out.println("1.회원정보출력 2.회원정보입력");
			System.out.print("선택>");
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 0) break;
			
			switch(choice) {
			case 1: service.menu01(sc); break;
			case 2: service.menu02(sc); break;
			}
		}
		
		System.out.println("프로그램 종료");
		sc.close();
	}

}
