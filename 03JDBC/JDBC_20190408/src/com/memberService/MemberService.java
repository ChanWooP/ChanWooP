package com.memberService;

import java.util.List;
import java.util.Scanner;

import com.member.Member;
import com.memberDAO.MemberDAO;

public class MemberService {
	
	private MemberDAO daom = new MemberDAO();
	
	public void menu01() {
		List<Member> list = daom.list();
		
		System.out.println("-----------------------");
		System.out.println("회원번호 / 이름 / 전화번호 / 이메일");
		for(Member m : list) {
			System.out.println(m.toString());
		}
		System.out.printf("총 %d명%n",list.size());
	}
	
	public void menu02(Scanner sc) {
		System.out.print("회원번호>");
		String mid = sc.nextLine();
		System.out.print("이름>");
		String name_ = sc.nextLine();
		System.out.print("전화번호>");
		String phone = sc.nextLine();
		System.out.print("이메일>");
		String email = sc.nextLine();

		System.out.println("정말입력하시겠습니까?");
		System.out.print("선택>");
		int c = sc.nextInt();
		sc.nextLine();
		
		if(c == 1) {
			int result = daom.add(new Member(mid, name_, phone, email));
			if(result == 0){
				System.out.println("입력실패");
			}else {
				System.out.println("입력성공");
			}
		}else {
			System.out.println("입력취소");
		}
	}
}
