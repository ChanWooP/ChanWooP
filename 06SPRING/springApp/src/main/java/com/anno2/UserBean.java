package com.anno2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserBean {
	@Autowired   // 타입으로 의존관계 설정. setter 필요 없음
	@Qualifier("userService2")  // 동일한 타입이 2이상인 경우 빈이름으로 설정.
	                            // 단, 단독으로 사용 불가하며 @Autowired와 함께 사용
	private UserService userService;
	
	public void write() {
		String s = userService.message();
		System.out.println(s);
	}
}
