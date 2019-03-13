package com.emp002.service;

import com.emp002.dao.DepartmentDAO;
import com.emp002.domain.Department;

import java.util.List;
import java.util.Scanner;

public class DepartmentService {
	private DepartmentDAO dao = new DepartmentDAO();

	public void menu(Scanner sc) {
		while(true) {
			System.out.println("직원 관리v1.0 / 2.기초정보관리 / 2.부서관리");
			System.out.println("-------------------------------------------");
			System.out.println("1.부서입력 2.부서출력");
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
		System.out.println("직원 관리v1.0 / 2.기초정보관리 / 2.부서관리 / 1.부서입력");
		this.print_();
		String deptNum = this.dao.generateDid();
		if(deptNum != null) {
			System.out.print("신규부서이름>");
			String deptName = sc.nextLine();
			System.out.print("정말입력하시겠습니까?(0/1)>");
			int choice = sc.nextInt();
			sc.nextLine();
			if(choice == 1) {
				Department department = new Department(deptNum, deptName);
				this.dao.add(department);
				System.out.println("입력완료!");
			}else {
				System.out.println("입력취소!");
			}
		}else {
			System.out.println("더이상 입력이 불가능 합니다");
		}
		
		
	}
	
	public void menu02(Scanner sc) {
		System.out.println("직원 관리v1.0 / 2.기초정보관리 / 2.부서관리 / 2.부서출력");
		this.print_();
	}
	
	private void print_() {
		List<Department> departments = this.dao.list();
	
		System.out.println("--------------------------------------------------------");
		System.out.println("부서번호 - 부서명");
		for(Department p : departments) {
			System.out.println(p.toString());
		}
		
		if(departments.size() == 0) System.out.println("자료가 없습니다");
		
	}
}
