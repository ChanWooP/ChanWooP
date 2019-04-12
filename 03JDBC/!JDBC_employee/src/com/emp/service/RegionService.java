package com.emp.service;

import java.util.List;
import java.util.Scanner;

import com.emp.domain.Region;
import com.emp.persistance.RegionDAO;

public class RegionService {
	private RegionDAO daoR = new RegionDAO();
	
	 public void menu(Scanner sc) {
		 while(true) {
			 System.out.println(">>직원관리>2.기초정보관리>1.지역관리");
			 System.out.println("1.지역입력 2.지역출력 3.지역삭제");
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
		 List<Region> list = daoR.list("", "");
		 print_(list);
		 
		 String regId = new RegionDAO().newRegionId();
		 System.out.print("신규 지역 이름>");
		 String reg_name = sc.nextLine();
		 int result = daoR.regionInsert(regId, reg_name);
		 
		 if(result != 0) {
			 System.out.println("신규 지역 정보 입력 완료");
		 }else {
			 System.out.println("신규 지역 정보 입력 실패");
		 }
	 }
	 
	 private void menu02(Scanner sc) {
		 List<Region> list = new RegionDAO().list("", "");
		 print_(list);
	 }
	 
	 private void menu03(Scanner sc) {
		 List<Region> list = new RegionDAO().list("", "");
		 String regId = "";
		 print_d(list);
		 while(true) {
			 System.out.print("지역번호>");
			 regId = sc.nextLine();
			 if(regIdCheck(list, regId)) break;
			 System.out.println("삭제할 수 없는 지역번호입니다.");
		 }
		 
		 System.out.print("정말 삭제 하시겠습니까?(0/1)");
		 int c = sc.nextInt();
		 sc.nextLine();
		 
		 if(c == 1) {
			 int result = daoR.regionDelete(regId);
			 if(result == 0) {
				 System.out.printf("%s 지역이 삭제가 실패했습니다.%n", regId);
			 }else {
				 System.out.printf("%s 지역이 삭제되었습니다.%n", regId);
			 }
			 
		 }else {
			 System.out.printf("%s 지역이 삭제가 취소되었습니다.%n", regId);
		 }
	 }
	 
	 private boolean regIdCheck(List<Region> list, String regId) {
		 boolean result = false;
		 for(Region r : list) {
			 if(regId.equals(r.getRegId()))
			 {
				 if(r.getCount_() == 0) {
					 result = true;
				 }
			 }
		 }
		 return result;
	 }
	 
	 private void print_(List<Region> list) {
		 System.out.println("-----------------");
		 System.out.println("지역번호 / 지역명");
		 for(Region r : list) {
			 System.out.println(r.toString());
			 //(조건식)?"값1":"값2"
		 }
		 if(list.size() == 0) {
			 System.out.println("자료가 없습니다");
		 }
	 }
	 
	 private void print_d(List<Region> list) {
		 System.out.println("-----------------");
		 System.out.println("지역번호 / 지역명");
		 for(Region r : list) {
			 System.out.printf("%s / %s%n",r.toString(), (r.getCount_() == 0)?"O":"X");
			 //(조건식)?"값1":"값2"
		 }
		 if(list.size() == 0) {
			 System.out.println("자료가 없습니다");
		 }
	 }
}
