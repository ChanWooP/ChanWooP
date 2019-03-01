package com.test;

public class Program109 {

	public static void main(String[] args) {
		
		// 선언
		String[][] members = new String[10][]; // 2차원 배열 선언
		
		//입력
		for(int a=0; a<10; ++a) {
			String[] member = new String[3]; // 값을 받기 위한 1차원 배열
			member[0] = "홍길동"+a;
			member[1] = "010-9999-999"+a;
			member[2] = "email@qwer.com"+a;
			
			members[a] = member; // members에 member를 넣는다
		}
		
		//출력
		for(int a=0; a<10; ++a) {
			String[] member = members[a]; // member에 members를 넣는다
			System.out.println(member[0]+" / "+member[1]+" / "+member[2]);
		}
	}

}
