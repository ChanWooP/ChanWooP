package com.anno2;

import javax.annotation.Resource;

public class UserBean2 {
	@Resource(name="userService1") // 빈이름을 가지고 의존관계설정. 자바 어노테이션
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		System.out.println(s);
	}
}
