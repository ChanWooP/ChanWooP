package com.test041;

public class Main {

	public static void main(String[] args) {
		
		Member[] members = new Member[10];
		
		Member m1 = new Member("M01","hong","010-1111-1111","hong@test.com");
		members[0] = m1;
		
		Member m2 = new Member();
		m2.setMid("M02");
		m2.setName("chan");
		m2.setPhone("010-1112-1111");
		m2.setEmail("chan@test.com");
		members[1] = m2;
		
		for(Member m : members) {
			if(m != null) {
				System.out.printf("%s / %s / %s / %s%n",
						m.getMid(),
						m.getName(),
						m.getPhone(),
						m.getEmail());
			}
		}
	}

}
