package com.memberService;

import java.util.List;

import com.member.Member;
import com.memberDAO.MemberDAO;

public class MemberService {
	
	MemberDAO daom = new MemberDAO();
	
	public void menu01() {
		List<Member> list = daom.list();
		
		System.out.println("-----------------------");
		System.out.println("회원번호 / 이름 / 전화번호 / 이메일");
		for(Member m : list) {
			System.out.println(m.toString());
		}
		System.out.printf("총 %d명%n",list.size());
	}
	
	public void menu02() {
		
	}
}
