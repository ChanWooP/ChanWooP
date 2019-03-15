package com.emp002.service;

import com.emp002.dao.PositionDAO;
import com.emp002.domain.Position;

import java.util.List;
import java.util.Scanner;

public class PositionService {
	private PositionDAO dao = new PositionDAO();
	
	public PositionService(PositionDAO daoP) {
		this.dao = daoP;
	}
	
	public void menu(Scanner sc) {
		while(true) {
			System.out.println("직원 관리v1.0 / 2.기초정보관리 / 3.직위관리");
			System.out.println("-------------------------------------------");
			System.out.println("1.직위입력 2.직위출력");
			System.out.print("선택(1~2, 0 quit)>");
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 0) break;
			
			switch(choice) {
			case 1: this.menu01(sc); break;
			case 2: this.menu02(sc); break;
			}
		}
	}

	public void menu01(Scanner sc) {
		System.out.println("직원 관리v1.0 / 2.기초정보관리 / 3.직위관리 / 1.직위입력");
		this.print_();
		String posNum = this.dao.generatePid();
		if(posNum != null) {
			System.out.print("신규직위이름>");
			String posName = sc.nextLine();
			System.out.print("기본급>");
			int posMoney = sc.nextInt();
			sc.nextLine();
			System.out.print("보너스>");
			int extraMoney = sc.nextInt();
			sc.nextLine();
			System.out.print("정말입력하시겠습니까?(0/1)>");
			int choice = sc.nextInt();
			sc.nextLine();
			if(choice == 1) {
				Position position = new Position(posNum, posName, posMoney, extraMoney);
				this.dao.add(position);
				System.out.println("입력완료!");
			}else {
				System.out.println("입력취소!");
			}
		}else {
			System.out.println("더이상 입력이 불가능 합니다");
		}
		
		
	}
	
	public void menu02(Scanner sc) {
		System.out.println("직원 관리v1.0 / 2.기초정보관리 / 3.직위관리 / 2.직위출력");
		this.print_();
	}
	
	private void print_() {
		List<Position> positions = this.dao.list();
	
		System.out.println("--------------------------------------------------------");
		System.out.println("직위번호 - 직위명 - 기본급 - 보너스");
		for(Position p : positions) {
			System.out.println(p.toString());
		}
		
		if(positions.size() == 0) System.out.println("자료가 없습니다");
		
	}
}
