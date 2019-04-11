package com.emp.service;

import java.util.Scanner;

import com.emp.persistance.LoginDAO;

public class LoginService {

	//관리자 로그인 메소드
	public void login(Scanner sc) {
		
		//아이디, 패스워드 질의
		//로그인 메소드 호출
		//결과에 따라서 
		//직원관리 액션 진행 또는 에러 메시지 출력
		System.out.print("아이디>");
		String id_ = sc.nextLine();
		System.out.print("패스워드>");
		String pw_ = sc.nextLine();
		
		String id = new LoginDAO().login(id_, pw_);
		
		if(id != null) {
			//관리자 로그인 성공시 액션
			System.out.printf("관리자(%s) 로그인되었습니다.%n", id_);
			new MainMenuService().mainMenu(sc, id);
			System.out.printf("관리자(%s) 로그아웃되었습니다.%n", id_);
		}else {
			System.out.println("로그인이 실패했습니다.");
		}		
	}

	
}
