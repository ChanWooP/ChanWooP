package com.anno1;

import org.springframework.beans.factory.annotation.Autowired;

public class UserBean {
	@Autowired   // Ÿ������ �������� ����. setter �ʿ� ����
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		System.out.println(s);
	}
}
