package com.test054;

import java.util.Scanner;

public class ScoreService {
	private ScoreDAO dao = new ScoreDAO();
	
	//성적입력 메소드
	public void menu01(Scanner sc) {
		if(dao.fullIdx()) {
			System.out.println("저장소가 꽉찼습니다");
		}else {
			System.out.println("1. 성적정보입력");
			System.out.println("---------------");
			System.out.print("번호 : ");
			String mid = sc.nextLine();
			System.out.print("이름 : ");
			String name = sc.nextLine();
			System.out.print("과목1 : ");
			int sub1 = sc.nextInt();
			sc.nextLine();
			System.out.print("과목2 : ");
			int sub2 = sc.nextInt();
			sc.nextLine();
			System.out.print("과목3 : ");
			int sub3 = sc.nextInt();
			sc.nextLine();
			
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
		Score[] list = dao.list();
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
		System.out.printf("총: %d건%n", count);
	}
	
	//성적검색 메소드
	public void menu03(Scanner sc) {

	}
	
	//번호기준 메소드
	public void menu03_1(Scanner sc) {

	}
	
	//아름기준 메소드
	public void menu03_2(Scanner sc) {

	}
	
	//출력 포맷 private 메소드
	private void print_(Score[] list) {
		
	}
}
