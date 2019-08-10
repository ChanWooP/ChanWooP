package com.anno5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// id를 지정하지 않으면 아이디는 클래스이름에서 첫글자를 소문자로한 클래스명이다. 즉 아래 클래스의
//   아이디는 userBean 이다.
@Component
public class UserBean {
	@Autowired
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		System.out.println(s);
	}
}
