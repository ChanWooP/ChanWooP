package com.emp002.service;

import java.util.List;
import java.util.Scanner;

import com.emp002.domain.*;
import com.emp002.dao.*;

public class EmployeeService {
	EmployeeDAO dao = new EmployeeDAO();
	RegionDAO daoR;
	DepartmentDAO daoD;
	PositionDAO daoP;
	
	public EmployeeService(RegionDAO daoR, DepartmentDAO daoD, PositionDAO daoP) {
		this.daoR = daoR;
		this.daoD = daoD;
		this.daoP = daoP;
	}
	
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
		
		String empId = dao.generateEid();
		if(empId != null) {
			//이름
			System.out.print("이름>");
			String empName = sc.nextLine();
			//주민번호 - 패턴검사
			System.out.print("주민번호>");
			String ssn = sc.nextLine();
			//입사일
			System.out.print("입사일>");
			String hiredate = sc.nextLine();
			//전화번호 - 패턴검사
			System.out.print("전화번호>");
			String phone = sc.nextLine();
			//지역번호 입력 및 지역명 확인
			List<Region> rlist = this.daoR.list();
			System.out.println("지역");
			System.out.println("----");
			for(Region r : rlist) {
				System.out.println(r.toString());
			}
			String regId = null;
			String regName = null;
			while(true) {
				System.out.print("지역번호>");
				regId = sc.nextLine();
				regName = this.daoR.getRegName(regId);
				if(regName == null) {
					System.out.println("잘못된 입력입니다");
				}else {
					break;
				}
			}
			
			List<Department> dlist = this.daoD.list();
			System.out.println("부서");
			System.out.println("----");
			for(Department d : dlist) {
				System.out.println(d.toString());
			}
			String deptId = null;
			String deptName = null;
			while(true) {
				System.out.print("부서번호>");
				deptId = sc.nextLine();
				deptName = this.daoD.getDeptName(deptId);
				if(deptName == null) {
					System.out.println("잘못된 입력입니다");
				}else {
					break;
				}
			}
			
			List<Position> plist = this.daoP.list();
			System.out.println("직위");
			System.out.println("----");
			for(Position p : plist) {
				System.out.println(p.toString());
			}
			
			String posId = null;
			String posName = null;
			int basicpay = 0;
			int extrapay = 0;
			while(true) {
				System.out.print("직위번호>");
				posId = sc.nextLine();
				posName = this.daoP.getPosName(posId);
				basicpay = this.daoP.getBasicPay(posId);
				extrapay = this.daoP.getExtraPay(posId);
				if(posName == null) {
					System.out.println("잘못된 입력입니다");
				}else {
					break;
				}
				
			}
			System.out.print("정말 입력하시겠습니까?(0/1)");
			int choice = sc.nextInt();
			sc.nextLine();
			if(choice == 1) {
				Employee e = new Employee(empId, empName, ssn, hiredate, phone
						,regId, deptId, posId, regName, deptName, posName
						,basicpay, extrapay);
				this.dao.add(e);
			}else {
				System.out.println("입력취소");
			}
			
		}else {
			System.out.println("입력이 불가능합니다");
		}
		
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
