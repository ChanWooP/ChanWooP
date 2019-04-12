package com.emp.service;

import java.util.List;
import java.util.Scanner;

import com.emp.domain.Job;
import com.emp.persistance.JobDAO;

public class JobService {
	private JobDAO daoj = new JobDAO();
	
	 public void menu(Scanner sc) {
		 while(true) {
			 System.out.println(">>직원관리>2.기초정보관리>3.관리");
			 System.out.println("1.직위입력 2.직위출력 3.직위삭제");
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
		 List<Job> list = daoj.list("", "");
		 print_(list);
		 
		 String jobId = daoj.newJobId();
		 System.out.print("신규 직위 이름>");
		 String job_title = sc.nextLine();
		 System.out.print("신규 직위 최소 금액>");
		 int min_basicpay = sc.nextInt();
		 sc.nextLine();
		 int result = daoj.jobInsert(jobId, job_title, min_basicpay);
		 
		 if(result != 0) {
			 System.out.println("신규 직위 입력 완료");
		 }else {
			 System.out.println("신규 직위 입력 실패");
		 }
	 }
	 
	 private void menu02(Scanner sc) {
		 List<Job> list = daoj.list("", "");
		 print_(list);
	 }
	 
	 private void menu03(Scanner sc) {
		 List<Job> list = daoj.list("", "");
		 String jobId = "";
		 print_d(list);
		 while(true) {
			 System.out.print("직위번호>");
			 jobId = sc.nextLine();
			 if(jobIdCheck(list, jobId)) break;
			 System.out.println("삭제할 수 없는 직위번호입니다.");
		 }
		 
		 System.out.print("정말 삭제 하시겠습니까?(0/1)");
		 int c = sc.nextInt();
		 sc.nextLine();
		 
		 if(c == 1) {
			 int result = daoj.jobDelete(jobId);
			 if(result == 0) {
				 System.out.printf("%s 직위 삭제가 실패했습니다.%n", jobId);
			 }else {
				 System.out.printf("%s 직위가 삭제되었습니다.%n", jobId);
			 }
			 
		 }else {
			 System.out.printf("%s 직위 삭제가 취소되었습니다.%n", jobId);
		 }
	 }
	 
	 private boolean jobIdCheck(List<Job> list, String jobId) {
		 boolean result = false;
		 for(Job r : list) {
			 if(jobId.equals(r.getJobId()))
			 {
				 if(r.getCount_() == 0) {
					 result = true;
				 }
			 }
		 }
		 return result;
	 }
	 
	 private void print_(List<Job> list) {
		 System.out.println("-----------------");
		 System.out.println("부서번호 / 부서명");
		 for(Job r : list) {
			 System.out.println(r.toString());
			 //(조건식)?"값1":"값2"
		 }
		 if(list.size() == 0) {
			 System.out.println("자료가 없습니다");
		 }
	 }
	 
	 private void print_d(List<Job> list) {
		 System.out.println("-----------------");
		 System.out.println("부서번호 / 부서명");
		 for(Job r : list) {
			 System.out.printf("%s / %s%n",r.toString(), (r.getCount_() == 0)?"O":"X");
			 //(조건식)?"값1":"값2"
		 }
		 if(list.size() == 0) {
			 System.out.println("자료가 없습니다");
		 }
	 }
}
