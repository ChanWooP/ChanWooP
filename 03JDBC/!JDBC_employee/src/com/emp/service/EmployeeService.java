package com.emp.service;

import java.util.List;
import java.util.Scanner;

import com.emp.domain.Employee;
import com.emp.persistance.EmployeeDAO;

public class EmployeeService {
	EmployeeDAO daoE = new EmployeeDAO();
	public void menu(Scanner sc) {
		while(true) {
			System.out.println(">>직원관리 v2.0 > 1.직원정보관리");
			System.out.println("1.직원입력  2.직원전체출력  3.직원검색  4.직원삭제");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();
			if (m == 0) break;
			switch (m) {
			case 1: menu11(sc);	break;
			case 2: menu12(sc);	break;
			case 3: menu13(sc);	break;
			case 4: menu14(sc);	break;
			}
		}
	}
	
	private void menu11(Scanner sc) {
		
	}
	
	private void menu12(Scanner sc) {
		while(true) {
			System.out.println(">>직원관리 v2.0 > 1.직원정보관리 > 2.직원전체출력");
			System.out.println("1.사번정렬 2.이름정렬 3.지역명정렬 4.부서명정렬 5.직위명정렬");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();
			if (m == 0) break;
			switch (m) {
			case 1: menu121();	break;
			case 2: menu122();	break;
			case 3: menu123();	break;
			case 4: menu124();	break;
			case 5: menu125();	break;
			}
		}
	}
	
	private void menu121() {
		System.out.println(">>직원관리 v2.0 > 1.직원정보관리 > 2.직원전체출력 > 1.사번정렬");
		List<Employee> list = daoE.list("sort", "empid");
		print_(list);
	}
	
	private void menu122() {
		System.out.println(">>직원관리 v2.0 > 1.직원정보관리 > 2.직원전체출력 > 2.이름정렬");
		List<Employee> list = daoE.list("sort", "name_");
		print_(list);
	}
	
	private void menu123() {
		System.out.println(">>직원관리 v2.0 > 1.직원정보관리 > 2.직원전체출력 > 3.지역명정렬");
		List<Employee> list = daoE.list("sort", "reg_name");
		print_(list);
	}
	
	private void menu124() {
		System.out.println(">>직원관리 v2.0 > 1.직원정보관리 > 2.직원전체출력 > 4.부서명정렬");
		List<Employee> list = daoE.list("sort", "dept_name");
		print_(list);
	}
	
	private void menu125() {
		System.out.println(">>직원관리 v2.0 > 1.직원정보관리 > 2.직원전체출력 > 5.직위명정렬");
		List<Employee> list = daoE.list("sort", "job_title");
		print_(list);
	}
	
	private void menu13(Scanner sc) {
		while(true) {
			System.out.println(">>직원관리 v2.0 > 1.직원정보관리 > 3.직원검색");
			System.out.println("1.사번검색 2.이름검색 3.지역명검색 4.부서명검색 5.직위명검색");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();
			if (m == 0) break;
			switch (m) {
			case 1: menu131(sc);	break;
			case 2: menu132(sc);	break;
			case 3: menu133(sc);	break;
			case 4: menu134(sc);	break;
			case 5: menu135(sc);	break;
			}
		}
	}	
	
	private void menu131(Scanner sc) {
		System.out.println(">>직원관리 v2.0 > 1.직원정보관리 > 3.직원검색 > 1.사번검색");
		System.out.print("검색>");
		String value = sc.nextLine();
		List<Employee> list = daoE.list("empid", value);
		print_(list);
	}
	
	private void menu132(Scanner sc) {
		System.out.println(">>직원관리 v2.0 > 1.직원정보관리 > 3.직원검색 > 2.이름검색");
		System.out.print("검색>");
		String value = sc.nextLine();
		List<Employee> list = daoE.list("name_", value);
		print_(list);
	}
	
	private void menu133(Scanner sc) {
		System.out.println(">>직원관리 v2.0 > 1.직원정보관리 > 3.직원검색 > 3.지역명검색");
		System.out.print("검색>");
		String value = sc.nextLine();
		List<Employee> list = daoE.list("reg_name", value);
		print_(list);
	}
	
	private void menu134(Scanner sc) {
		System.out.println(">>직원관리 v2.0 > 1.직원정보관리 > 3.직원검색 > 4.부서명검색");
		System.out.print("검색>");
		String value = sc.nextLine();
		List<Employee> list = daoE.list("dept_name", value);
		print_(list);
	}
	
	private void menu135(Scanner sc) {
		System.out.println(">>직원관리 v2.0 > 1.직원정보관리 > 3.직원검색 > 5.직위명검색");
		System.out.print("검색>");
		String value = sc.nextLine();
		List<Employee> list = daoE.list("job_title", value);
		print_(list);
	}
	
	private void menu14(Scanner sc) {
		
	}
	
	private void print_(List<Employee> list) {
		System.out.printf("전체 인원 : %d명%n", list.size());
		System.out.println("---------------- ");
		System.out.println("사번 / 이름 / 주민번호 / 입사일 / 전화번호 / 지역명 / 부서명 / 직위명 / 기본급 / 수당 / 급여");
		for(Employee e : list) {
			System.out.println(e.toString());
		}
		if(list.size() == 0) {
			System.out.println("자료가 없습니다.");
		}
	}

}
