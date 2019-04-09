package com.score;

import java.util.List;
import java.util.Scanner;

public class ScoreService {
	
	ScoreDAO daoS;
	
	public ScoreService() {
		this.daoS  = new ScoreDAO();
	}
	
	public void menu01(Scanner sc) {
		System.out.print("회원번호>");
		String sid_ = sc.nextLine();
		System.out.print("이름>");
		String name_ = sc.nextLine();
		int sub1, sub2, sub3;
		while(true) {
			System.out.print("과목1>");
			sub1 = sc.nextInt();
			sc.nextLine();
			
			if(sub1<0 || sub1>100) {
				System.out.println("0~100 사이의 점수만 입력해주세요.");
			}else {
				break;
			}
		}
		while(true) {
			System.out.print("과목2>");
			sub2 = sc.nextInt();
			sc.nextLine();
			
			if(sub2<0 || sub2>100) {
				System.out.println("0~100 사이의 점수만 입력해주세요.");
			}else {
				break;
			}
		}
		while(true) {
			System.out.print("과목3>");
			sub3 = sc.nextInt();
			sc.nextLine();
			
			if(sub3<0 || sub3>100) {
				System.out.println("0~100 사이의 점수만 입력해주세요.");
			}else {
				break;
			}
		}

		System.out.println("정말입력하시겠습니까?");
		System.out.print("선택>");
		int c = sc.nextInt();
		sc.nextLine();
		
		if(c == 1) {
			int result = daoS.add(new Score(sid_, name_, sub1, sub2, sub3));
			if(result == 0){
				System.out.println("입력실패");
			}else {
				System.out.println("입력성공");
			}
		}else {
			System.out.println("입력취소");
		}
	}
	
	public void menu02() {
		String key = "all";
		String value = "";
		
		System.out.println("2.성적정보출력");
		this.print_(key, value);
	}
	
	public void menu03(Scanner sc) {
		while(true) {
			System.out.println("3.성적정보검색");
			System.out.println("---------------------------------------------");
			System.out.println("1.번호기준 2.이룸기준");
			System.out.print("선택>");
			int c = sc.nextInt();
			sc.nextLine();
			
			
			if(c==0) break;
			
			System.out.print("검색>");
			String value = sc.nextLine();
			
			switch(c) {
			case 1: this.menu03_1(value); break;
			case 2: this.menu03_2(value); break;
			}
		}
	}
	
	private void menu03_1(String value) {
		String key = "sid_";
		
		System.out.println("3.성적정보검색 / 1.번호기준");
		this.print_(key, value);
	}
	
	private void menu03_2(String value) {
		String key = "name_";
		
		System.out.println("3.성적정보검색 / 2.이름기준");
		this.print_(key, value);
	}
	
	private void print_(String key, String value) {
		List<Score> result = daoS.list(key, value);
		
		System.out.println("---------------------------------------------");
		System.out.println("번호 / 이름 / 과목1 / 과목2 / 과목3 / 총점 / 평균 / 석차");
		for(Score s : result) {
			System.out.println(s.toString());
		}
		
		if(result.size() == 0) {
			System.out.println("출력결과가 없습니다.");
		}else {
			System.out.printf("총 %d명%n",result.size());
		}	
		
	}
}
