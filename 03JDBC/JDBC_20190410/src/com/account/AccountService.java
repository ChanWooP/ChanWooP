package com.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AccountService {
	
	private AccountDAO daoA = new AccountDAO();
	private String adminPW = "1234";
	
	public void menu01(Scanner sc) {
		System.out.println("계좌조회 작업을 진행합니다.");
		System.out.print("게좌번호>");
		String account_ = sc.nextLine();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", account_);
		
		System.out.printf("(%s)님의 계좌목록%n",account_);
		
		List<AccountList> result = this.daoA.list("account", map);
		accountList(result);
	}
	
	public void menu02(Scanner sc) {
		System.out.println("계좌입금 작업을 진행합니다.");
		System.out.print("계좌번호>");
		String account_ = sc.nextLine();
		System.out.print("입금액>");
		int money = sc.nextInt();
		sc.nextLine();
		
		int balance = this.daoA.getBalance(account_);
		int result = this.daoA.deposit(new AccountHistory(account_, money, balance));
		
		if(result >= 0) {
			System.out.println("입금이 성공했습니다.");
		}else {
			System.out.println("입금이 실패했습니다.");
		}
	}
	
	public void menu03(Scanner sc) {
		System.out.println("계좌출금 작업을 진행합니다.");
		System.out.print("계좌번호>");
		String account_ = sc.nextLine();
		System.out.print("출금액>");
		int money = sc.nextInt();
		sc.nextLine();
		System.out.print("비밀번호>");
		String pw = sc.nextLine();
		
		boolean pwcheck = this.daoA.pwCheck(new Account(account_, pw));
		
		if(pwcheck == true) {
			int balance = this.daoA.getBalance(account_);
			
			if(balance < money) {
				System.out.println("잔액이 부족합니다.");
			}else {
				int result = this.daoA.withdraw(new AccountHistory(account_, money, balance));
				
				if(result >= 0) {
					System.out.println("입금이 성공했습니다.");
				}else {
					System.out.println("입금이 실패했습니다.");
				}
			}
		}else {
			System.out.println("비밀번호가 틀렸습니다.");
		}
	}
	
	public void adminCheck(Scanner sc) {
		System.out.println("관리자 전용 화면으로 이동합니다.");
		System.out.print("관리자비밀번호>");
		String pw = sc.nextLine();
		if(adminPW.equals(pw)) {
			this.menu99(sc);
		}else {
			System.out.println("비밀번호가 틀렸습니다.");
		}
	}
	
	private void menu99(Scanner sc) {
		while(true) {
			System.out.println("--계좌관리_관리자--");
			System.out.println("1.계좌생성 2.계좌조회 3.계좌입출금내역 4.종료");
			System.out.print("선택>");
			int c = sc.nextInt();
			sc.nextLine();
			
			if(c == 4) break;
			
			switch(c) {
			case 1: break;
			case 2: this.menu99_2(sc); break;
			case 3: break;
			}
		}
	}
	
	private void menu99_2(Scanner sc) {
		while(true) {
			System.out.println("--계좌관리_관리자--");
			System.out.println("2.계좌조회");
			System.out.println("1.전체계좌 2.계좌번호 3.계좌주  4.종료");
			System.out.print("선택>");
			int c = sc.nextInt();
			sc.nextLine();
			
			if(c == 4) break;
			
			switch(c) {
			case 1: this.menu99_2_1(sc); break;
			case 2: this.menu99_2_2(sc); break;
			case 3: this.menu99_2_3(sc); break;
			}
		}
	}
	
	private void menu99_2_1(Scanner sc) {
		Map<String, String> map = new HashMap<String, String>();
		
		System.out.println("전체계좌 목록");
		
		List<AccountList> result = this.daoA.list("all", map);
		accountList(result);
	}
	
	private void menu99_2_2(Scanner sc) {
		System.out.print("게좌번호>");
		String account_ = sc.nextLine();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", account_);
		
		System.out.printf("(%s)님의 계좌목록%n",account_);
		
		List<AccountList> result = this.daoA.list("account", map);
		accountList(result);
	}
	
	private void menu99_2_3(Scanner sc) {
		System.out.print("이름>");
		String name = sc.nextLine();
		System.out.print("전화번호>");
		String phone = sc.nextLine();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", name);
		map.put("2", phone);
		
		System.out.printf("(%s/%s)님의 계좌목록%n",name, phone);
		
		List<AccountList> result = this.daoA.list("name/phone", map);
		accountList(result);
	}
	
	private void accountList(List<AccountList> list) {
		System.out.println("-----------------------------------------------");
		System.out.println("게좌번호 / 잔액 / 계좌생성일 / 마지막거래일 / 계좌주 / 전화번호");
		for(AccountList a : list) {
			System.out.println(a.toString());
		}
		
		if(list.size() != 0){
			System.out.printf("총 %d 건%n",list.size());
		}else {
			System.out.printf("자료가 없습니다.%n");
		}
	}
}
