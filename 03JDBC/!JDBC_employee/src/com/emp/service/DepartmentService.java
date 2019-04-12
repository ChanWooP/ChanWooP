package com.emp.service;

import java.util.List;
import java.util.Scanner;

import com.emp.domain.Department;
import com.emp.domain.Region;
import com.emp.persistance.DepartmentDAO;

public class DepartmentService {
	private DepartmentDAO daoD = new DepartmentDAO();
	
	 public void menu(Scanner sc) {
		 while(true) {
			 System.out.println(">>직원관리>2.기초정보관리>2.부서관리");
			 System.out.println("1.부서입력 2.부서출력 3.부서삭제");
			 System.out.print("선택>");
			 int c = sc.nextInt();
			 sc.nextLine();
			 
			 if(c == 0) break;
			 
			 switch(c) {
			 case 1: this.menu01(sc); break;
			 case 2: this.menu02(sc); break;
			 case 3: this.menu03(sc); break;
			 }
		 }
	 }
	 
	 private void menu01(Scanner sc) {
		 List<Department> list = daoD.list("", "");
		 print_(list);
		 
		 String deptId = daoD.newDepartmentId();
		 System.out.print("신규 부서 이름>");
		 String dept_name = sc.nextLine();
		 int result = daoD.departmentInsert(deptId, dept_name);
		 
		 if(result != 0) {
			 System.out.println("신규 부서 입력 완료");
		 }else {
			 System.out.println("신규 부서 입력 실패");
		 }
	 }
	 
	 private void menu02(Scanner sc) {
		 List<Department> list = daoD.list("", "");
		 print_(list);
	 }
	 
	 private void menu03(Scanner sc) {
		 List<Department> list = daoD.list("", "");
		 String deptId = "";
		 print_d(list);
		 while(true) {
			 System.out.print("부서번호>");
			 deptId = sc.nextLine();
			 if(deptIdCheck(list, deptId)) break;
			 System.out.println("삭제할 수 없는 부서번호입니다.");
		 }
		 
		 System.out.print("정말 삭제 하시겠습니까?(0/1)");
		 int c = sc.nextInt();
		 sc.nextLine();
		 
		 if(c == 1) {
			 int result = daoD.departmentDelete(deptId);
			 if(result == 0) {
				 System.out.printf("%s 부서 삭제가 실패했습니다.%n", deptId);
			 }else {
				 System.out.printf("%s 부서가 삭제되었습니다.%n", deptId);
			 }
			 
		 }else {
			 System.out.printf("%s 부서 삭제가 취소되었습니다.%n", deptId);
		 }
	 }
	 
	 private boolean deptIdCheck(List<Department> list, String deptId) {
		 boolean result = false;
		 for(Department r : list) {
			 if(deptId.equals(r.getDeptId()))
			 {
				 if(r.getCount_() == 0) {
					 result = true;
				 }
			 }
		 }
		 return result;
	 }
	 
	 private void print_(List<Department> list) {
		 System.out.println("-----------------");
		 System.out.println("부서번호 / 부서명");
		 for(Department r : list) {
			 System.out.println(r.toString());
			 //(조건식)?"값1":"값2"
		 }
		 if(list.size() == 0) {
			 System.out.println("자료가 없습니다");
		 }
	 }
	 
	 private void print_d(List<Department> list) {
		 System.out.println("-----------------");
		 System.out.println("부서번호 / 부서명");
		 for(Department r : list) {
			 System.out.printf("%s / %s%n",r.toString(), (r.getCount_() == 0)?"O":"X");
			 //(조건식)?"값1":"값2"
		 }
		 if(list.size() == 0) {
			 System.out.println("자료가 없습니다");
		 }
	 }
}
