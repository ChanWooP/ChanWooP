package com.test033;

import java.util.Scanner;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	public void menu01(Scanner sc) {
		Member[] list = dao.list();
		
		if(dao.getIdx() != 0) {
			for(Member m : list) {
				if(m != null) {
					System.out.printf("%s / %s / %s / %s%n",m.getMid(),m.getName(),m.getPhone(),m.getEmail());
				}
			}
		}else {
			System.out.println("회원이 없습니다!");
		}
		
	}

	public void menu02(Scanner sc) {
		
		if(dao.getIdx() < 10) {
			System.out.print("회원번호>");
			String mid = sc.nextLine();
			System.out.print("이름>");
			String name = sc.nextLine();
			System.out.print("전화번호>");
			String phone = sc.nextLine();
			System.out.print("이메일>");
			String email = sc.nextLine();
			
			Member m = new Member();
			m.setMid(mid);
			m.setName(name);
			m.setPhone(phone);
			m.setEmail(email);
			
			System.out.print("정말 입력 하시겠습니까?(0/1)>");
			int yn = sc.nextInt();
			sc.nextLine();
			
			if(yn == 1) {
				dao.add(m);
			}else {
				System.out.println("입력 취소!");
			}
			
			
		}else {
			System.out.println("더이상 입력 불가능!");
		}
		
	}
	
	
}
