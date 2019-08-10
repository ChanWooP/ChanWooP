package com.user4;

public class UserBean2 {
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void write() {
		String s=userService.message();
		System.out.println("°á°ú => " + s);
	}
}
