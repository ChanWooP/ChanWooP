package com.anno1;

import org.springframework.beans.factory.annotation.Autowired;

public class UserBean {
	@Autowired   // 타입으로 의존관계 설정. setter 필요 없음
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		System.out.println(s);
	}
}
