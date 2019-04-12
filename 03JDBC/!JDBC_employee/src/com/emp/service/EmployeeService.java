package com.emp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.emp.domain.Department;
import com.emp.domain.Employee;
import com.emp.domain.Job;
import com.emp.domain.Region;
import com.emp.persistance.DepartmentDAO;
import com.emp.persistance.EmployeeDAO;
import com.emp.persistance.JobDAO;
import com.emp.persistance.RegionDAO;

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
		System.out.println("직원 정보 입력 과정입니다.");
		
		String empId = daoE.newEmpId();
		
		System.out.print("이름>");
		String name_ = sc.nextLine();
		
		System.out.print("주민번호>");
		String ssn = sc.nextLine();
		
		String hiredate = "";
		while(true) {
			System.out.print("입사일(YYYY-MM-DD)>");
			hiredate = sc.nextLine();
			if(this.dateCheck(hiredate)) break;
			System.out.println("날짜 정보가 틀렸습니다.");
		}
		
		System.out.print("전화번호>");
		String phone = sc.nextLine();
		
		String regId = "";
		List<Region> listR = new RegionDAO().list("", "");
		System.out.println("-----------------");
		System.out.println("지역번호 / 지역명");
		for(Region r : listR) {
			System.out.printf("%s / %s%n",r.getRegId(), r.getReg_name());
		}
		while(true) {
			System.out.print("지역번호>");
			regId = sc.nextLine();
			if(regIdCheck(listR, regId)) break;
			System.out.println("지역번호 범위가 아닙니다.");
		}
		
		String deptId = "";
		List<Department> listD = new DepartmentDAO().list("", "");
		System.out.println("-----------------");
		System.out.println("부서번호 / 부서명");
		for(Department d : listD) {
			System.out.printf("%s / %s%n",d.getDeptId(), d.getDept_name());
		}
		while(true) {
			System.out.print("부서번호>");
			deptId = sc.nextLine();
			if(deptIdCheck(listD, deptId)) break;
			System.out.println("부서번호 범위가 아닙니다.");
		}
		
		String jobId = "";
		int basicpay = 0;
		List<Job> listJ = new JobDAO().list("", "");
		System.out.println("-----------------");
		System.out.println("직위번호 / 직위명 / 최소기본급");
		for(Job j : listJ) {
			System.out.printf("%s / %s / %d%n",j.getJobId(), j.getJob_name(), j.getMin_basicPay());
		}
		while(true) {
			System.out.print("직위번호>");
			jobId = sc.nextLine();
			if(jobIdCheck(listJ, jobId)) break;
			System.out.println("직위번호 범위가 아닙니다.");
		}
		while(true) {
			System.out.print("기본급>");
			basicpay = sc.nextInt();
			sc.nextLine();
			if(jobpayCheck(listJ, jobId, basicpay)) break;
			System.out.println("최소기본급 범위가 아닙니다.");
		}
		System.out.print("수당>");
		int extrapay = sc.nextInt();
		sc.nextLine();
		
		int result = daoE.empInsert(new Employee(empId, name_, ssn, hiredate, phone, regId, deptId, jobId, basicpay, extrapay));
		
		if(result != 0) {
			System.out.println("추가완료!");
		}else {
			System.out.println("추가실패!");
		}
		
	}
	
	 private boolean regIdCheck(List<Region> list, String regId) {
		 boolean result = false;
		 for(Region r : list) {
			 if(regId.equals(r.getRegId()))
			 {
				 result = true;
			 }
		 }
		 return result;
	 }
	 
	 private boolean deptIdCheck(List<Department> list, String deptId) {
		 boolean result = false;
		 for(Department r : list) {
			 if(deptId.equals(r.getDeptId()))
			 {
				 result = true;
			 }
		 }
		 return result;
	 }
	 
	 private boolean jobIdCheck(List<Job> list, String jobId) {
		 boolean result = false;
		 for(Job r : list) {
			 if(jobId.equals(r.getJobId()))
			 {
				 result = true;
			 }
		 }
		 return result;
	 }
	 
	 private boolean jobpayCheck(List<Job> list, String jobId, int basicpay) {
		 boolean result = false;
		 for(Job r : list) {
			 if(r.getJobId().equals(jobId)) {
				 if(r.getMin_basicPay() <= basicpay) {
					 result = true;
				 }
			 }
		 }
		 return result;
	 }
	
	private boolean dateCheck(String date_) {
		boolean result = false;
		
		String[] temp = date_.split("-");
		int year = Integer.parseInt(temp[0]);
		int month = Integer.parseInt(temp[1]);
		int day = Integer.parseInt(temp[2]);
		
		try {
			LocalDate ld = LocalDate.of(year,month,day);
			result = true;
		}catch(Exception e){
		}
		
		return result;
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
		System.out.print("직원번호>");
		String empId = sc.nextLine();
		System.out.print("정말삭제하시겠습니까(0/1)>");
		int c = sc.nextInt();
		sc.nextLine();
		
		if(c == 1) {
			int result = daoE.empDelete(empId);
			if(result == 0) {
				System.out.println("삭제 실패");
			}else {
				System.out.println("삭제 성공");
			}
		}else {
			System.out.println("삭제 취소");
		}
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
