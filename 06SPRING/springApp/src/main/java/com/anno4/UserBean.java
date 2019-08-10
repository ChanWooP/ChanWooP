package com.anno4;

import org.springframework.beans.factory.annotation.Autowired;

public class UserBean {
	@Autowired
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		System.out.println(s);
	}
}
