package com.emp.service;

import java.util.Scanner;

public class RegionService {
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
		 
	 }
	 
	 private void menu02(Scanner sc) {
		 
	 }
	 
	 private void menu03(Scanner sc) {
		 
	 }
}
