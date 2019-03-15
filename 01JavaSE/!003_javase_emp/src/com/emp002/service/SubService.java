package com.emp002.service;

import java.util.Scanner;

import com.emp002.dao.DepartmentDAO;
import com.emp002.dao.PositionDAO;
import com.emp002.dao.RegionDAO;

public class SubService {
	
	RegionDAO daoR;
	DepartmentDAO daoD;
	PositionDAO daoP;
	
	public SubService(RegionDAO daoR, DepartmentDAO daoD, PositionDAO daoP) {
		this.daoR = daoR;
		this.daoD = daoD;
		this.daoP = daoP;
	}
	
	public void menu(Scanner sc) {
		RegionService serviceR = new RegionService(daoR);
		DepartmentService serviceD = new DepartmentService(daoD);
		PositionService serviceP = new PositionService(daoP);
		
		while(true) {
			System.out.println("직원관리v1.0 / 2.기초정보관리");
			System.out.println("-----------------------------");
			System.out.println("1.지역관리 2.부서관리 3.직위관리");
			System.out.print("선택(1~3, 0 quit)>");
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 0) break;
			
			switch(choice) {
			case 1: serviceR.menu(sc); break;
			case 2:	serviceD.menu(sc); break;
			case 3: serviceP.menu(sc); break;
			}
		}
	}
}
