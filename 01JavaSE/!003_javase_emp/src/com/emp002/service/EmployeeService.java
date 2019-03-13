package com.emp002.service;

import java.util.List;
import java.util.Scanner;

import com.emp002.dao.EmployeeDAO;
import com.emp002.domain.Employee;

public class EmployeeService {
	EmployeeDAO dao = new EmployeeDAO();
	
	public void main(Scanner sc) {
		while (true) {

			System.out.println();
			System.out.println("--------------------------------");
			System.out.println("직원 관리 v1.0/1.직원관리");
			System.out.println("1.직원입력  2.직원출력  3.직원검색");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0)	break;

			switch (m) {
			case 1:	this.menu1_1(sc); break;
			case 2: this.menu1_2(sc); break;
			case 3: this.menu1_3(sc); break;
			}

		}		
	}

	private void menu1_1(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	//직원 관리 v1.0/1.직원관리/2.직원출력
	private void menu1_2(Scanner sc) {
		while (true) {

			System.out.println();
			System.out.println("--------------------------------");
			System.out.println("직원 관리 v1.0/1.직원관리/2.직원출력");
			System.out.println("1.사번기준 2.이름기준 3.지역기준 4.부서기준 5.직위기준");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0)	break;

			switch (m) {
			case 1:	this.menu1_2_1(sc); break;
			case 2: this.menu1_2_2(sc); break;
			case 3: this.menu1_2_3(sc); break;
			case 4: this.menu1_2_4(sc); break;
			case 5: this.menu1_2_5(sc); break;
			}

		}
	}

	//직원 관리 v1.0/1.직원관리/2.직원출력/1.사번기준
	private void menu1_2_1(Scanner sc) {
		String key = "empId";
		List<Employee> list = dao.list(key);
		this.print(list);
		
	}

	private void menu1_2_2(Scanner sc) {
		String key = "empName";
		List<Employee> list = dao.list(key);
		this.print(list);
	}

	private void menu1_2_3(Scanner sc) {
		String key = "regId";
		List<Employee> list = dao.list(key);
		this.print(list);
	}

	private void menu1_2_4(Scanner sc) {
		String key = "deptId";
		List<Employee> list = dao.list(key);
		this.print(list);
	}

	private void menu1_2_5(Scanner sc) {
		String key = "posId";
		List<Employee> list = dao.list(key);
		this.print(list);
	}

	private void menu1_3(Scanner sc) {
		// TODO Auto-generated method stub
		
	}
	
	//출력 전용 메소드
	private void print(List<Employee> list) {
		//제목 출력
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println("사번 / 이름 / 주민번호 / 입사일 / 전화번호 / 지역명 / 부서명 / 직위명 / 기본급 / 수당 / 급여");
		//내용 출력
		for (Employee e : list) {
			//사번/이름/..../급여
			System.out.printf("%s / %s / %s / %s / %s / %s / %s / %s / %,d / %,d / %,d%n"
					, e.getEmpId(), e.getEmpName(), e.getSsn(), e.getHiredate(), e.getPhone()
					, e.getRegName(), e.getDeptName(), e.getPosName(), e.getBasicpay(), e.getExtrapay(),e.getPay());
		}
	}
}
