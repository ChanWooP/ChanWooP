package com.anno8;

import org.springframework.beans.factory.annotation.Autowired;

public class UserBean {
	@Autowired 
	private UserService userService;
	
	public void init() {
		System.out.println("init...");
	}
	
	public void write() {
		String s = userService.message();
		System.out.println(s);
	}
	
	public void destroy() {
		System.out.println("destroy...");
	}
	
	
}
