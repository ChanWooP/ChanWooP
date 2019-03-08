package com.score4;

import java.util.Scanner;

public class ScoreService {
	private ScoreDAO dao = new ScoreDAO();
	
	//성적입력 메소드
	public void menu01(Scanner sc) {
		if(dao.fullIdx()) {
			System.out.println("저장소가 꽉찼습니다");
		}else {
			int sub1, sub2, sub3;
			System.out.println("1. 성적정보입력");
			System.out.println("---------------");
			System.out.print("번호 : ");
			String mid = sc.nextLine();
			System.out.print("이름 : ");
			String name = sc.nextLine();
			
			while(true) {
				System.out.print("과목1 : ");
				sub1 = sc.nextInt();
				sc.nextLine();
				if(sub1 >=  0 && sub1 <= 100) {
					break;
				}else {
					System.out.println("과목점수는 0~100 사이로 입력해주세요");
				}
			}
			
			while(true) {
				System.out.print("과목2 : ");
				sub2 = sc.nextInt();
				sc.nextLine();
				if(sub2 >=  0 && sub2 <= 100) {
					break;
				}else {
					System.out.println("과목점수는 0~100 사이로 입력해주세요");
				}
			}
			
			while(true) {
				System.out.print("과목3 : ");
				sub3 = sc.nextInt();
				sc.nextLine();
				if(sub3 >=  0 && sub3 <= 100) {
					break;
				}else {
					System.out.println("과목점수는 0~100 사이로 입력해주세요");
				}
			}
			
			System.out.print("정말 입력하시겠습니까?(0/1)");
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 1) {
				Score s = new Score(mid, name, sub1, sub2, sub3);
				dao.add(s);
				System.out.println("입력이 완료되었습니다");
			}else {
				System.out.println("입력이 취소되었습니다");
			}
		}
		
	}
	
	//성적츨력 메소드
	public void menu02(Scanner sc) {
		System.out.println("2. 성적정보출력");
		System.out.println("---------------");
		
		Score[] list = dao.list();
		print_(list);
	}
	
	//성적검색 메소드
	public void menu03(Scanner sc) {
		
		while(true) {
			System.out.println("3.성적정보검색");
			System.out.println("--------------");
			System.out.println("1.번호기준 2.이름기준");
			System.out.print("선택>");
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 0) break;
			
			switch(choice) {
			case 1: menu03_1(sc); break;
			case 2: menu03_2(sc); break;
			}
		}
		
	}
	
	//번호기준 메소드
	public void menu03_1(Scanner sc) {
		String key = "mid";
		System.out.print("검색>");
		String value = sc.nextLine();
		System.out.println("3.성적정보검색 / 1.번호기준");
		System.out.println("---------------------------");
		
		Score[] list = dao.list(key, value);
		print_(list);
	}
	
	//아름기준 메소드
	public void menu03_2(Scanner sc) {
		String key = "name";
		System.out.print("검색>");
		String value = sc.nextLine();
		System.out.println("3.성적정보검색 / 2.이름기준");
		System.out.println("---------------------------");

		Score[] list = dao.list(key, value);
		print_(list);	
	}
	
	//출력 포맷 private 메소드
	private void print_(Score[] list) {
		int count = 0;
		
		System.out.println("번호 / 이름 / 과목1 / 과목2 / 과목3 / 총점 / 평균 / 석차");
		for(Score m : list) {
			if(m != null)
			{
				System.out.printf("%4s / %3s / %3d / %3d / %3d / %3d / %5.1f / %2d%n"
						,m.getMid(), m.getName(), m.getSub1(), m.getSub2(), m.getSub3()
						,m.getTotal(), m.getAvg_(), m.getRank_());
				++count;
			}
			
		}
		if(count == 0) {
			System.out.println("자료가 없습니다.");
		}
		System.out.printf("총: %d건%n", count);
	}
}
