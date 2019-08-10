package com.anno3;

import javax.inject.Inject;

/*
 	- @Autowired : 스프링 전용. 타입으로 의존관계 설정
 	- @Inject : 자바에서 지원. 타입으로 의존관계 설정. 아래의 dependency 필요
 	  <dependency>
	    <groupId>javax.inject</groupId>
	    <artifactId>javax.inject</artifactId>
	    <version>1</version>
	  </dependency>
 	- @Resource : 자바에서 지원. 빈이름으로 의존관계 설정
 	- 스프링 : 빈이름으로 의존관계설정
 	  @Autowired
	  @Qualifier("빈이름")
*/

public class UserBean {
	@Inject
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		System.out.println(s);
	}
}
