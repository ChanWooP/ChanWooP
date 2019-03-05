package com.test032;

public class Main {

	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();
		
		Member mem = new Member();
		mem.setMid("M002");
		mem.setName("hong2");
		mem.setPhone("010-1111-1111");
		mem.setEmail("qwer@naver.com");
		dao.add(mem); // 자료입력용 메소드
		
		Member[] list = dao.list(); // list메소드에서 사본 배열 반환
		
		for(Member m : list) {
			if(m != null) {
				System.out.printf("%s / %s / %s / %s%n",m.getMid(),m.getName(),m.getPhone(),m.getEmail());
			}
		}
		
	}

}
