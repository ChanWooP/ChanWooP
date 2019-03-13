package com.emp002.service;

import com.emp002.dao.RegionDAO;
import com.emp002.domain.Region;

import java.util.List;
import java.util.Scanner;

public class RegionService {
	private RegionDAO dao = new RegionDAO();

	public void menu(Scanner sc) {
		while(true) {
			System.out.println("직원 관리v1.0 / 2.기초정보관리 / 1.지역관리");
			System.out.println("-------------------------------------------");
			System.out.println("1.지역입력 2.지역출력");
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
		System.out.println("직원 관리v1.0 / 2.기초정보관리 / 1.지역관리 / 1.지역입력");
		this.print_();
		String regNum = this.dao.generateRid();
		if(regNum != null) {
			System.out.print("신규지역이름>");
			String regName = sc.nextLine();
			System.out.print("정말입력하시겠습니까?(0/1)>");
			int choice = sc.nextInt();
			sc.nextLine();
			if(choice == 1) {
				Region region = new Region(regNum, regName);
				this.dao.add(region);
				System.out.println("입력완료!");
			}else {
				System.out.println("입력취소!");
			}
		}else {
			System.out.println("더이상 입력이 불가능 합니다");
		}
		
		
	}
	
	public void menu02(Scanner sc) {
		System.out.println("직원 관리v1.0 / 2.기초정보관리 / 1.지역관리 / 2.지역출력");
		this.print_();
	}
	
	private void print_() {
		List<Region> regions = this.dao.list();
	
		System.out.println("--------------------------------------------------------");
		System.out.println("지역번호 - 지역명");
		for(Region r : regions) {
			System.out.println(r.toString());
		}
		
		if(regions.size() == 0) System.out.println("자료가 없습니다");
		
	}
	
	
}
