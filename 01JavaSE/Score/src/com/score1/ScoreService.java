package com.score1;

import java.util.Scanner;

public class ScoreService {
	private ScoreDAO dao = new ScoreDAO();
	
	//성적입력 메소드
	public void menu01(Scanner sc) {

	}
	
	//성적츨력 메소드
	public void menu02(Scanner sc) {
		Score[] list = dao.list();
		
		System.out.println("번호 / 이름 / 과목1 / 과목2 / 과목3 / 총점 / 평균 / 석차");
		for(Score m : list) {
			if(m != null)
			{
				System.out.printf("%4s / %3s / %3d / %3d / %3d / %3d / %5.1f / %2d%n"
						,m.getMid(), m.getName(), m.getSub1(), m.getSub2(), m.getSub3()
						,m.getTotal(), m.getAvg_(), m.getRank_());
			}
			
		}
		System.out.printf("총: %d건%n", dao.getIdx());
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
