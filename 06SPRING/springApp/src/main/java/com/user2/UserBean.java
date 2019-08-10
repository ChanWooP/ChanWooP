package com.user2;

public class UserBean {
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void write() {
		String s=userService.message();
		System.out.println("°á°ú => " + s);
	}
}
