package com.anno2;

import javax.annotation.Resource;

public class UserBean2 {
	@Resource(name="userService1") // ���̸��� ������ �������輳��. �ڹ� ������̼�
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		System.out.println(s);
	}
}
