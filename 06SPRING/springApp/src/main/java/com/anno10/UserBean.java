package com.anno10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // ��ü����
public class UserBean {
	@Autowired 
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		System.out.println(s);
	}
}
