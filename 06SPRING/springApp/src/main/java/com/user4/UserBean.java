package com.user4;

public class UserBean {
	private UserService userService;
	
	public UserBean(UserService userService) {
		this.userService=userService;
	}
	
	public void write() {
		String s=userService.message();
		System.out.println("°á°ú => " + s);
	}
}
