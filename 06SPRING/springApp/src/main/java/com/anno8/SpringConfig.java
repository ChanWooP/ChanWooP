package com.anno8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	// 빈이름을 명시하지 않으면 빈이름은 메소드명(userDevice)이 된다.
	@Bean(name="userBean", initMethod="init", destroyMethod="destroy")
	public UserBean userDevice() {
		return new UserBean();
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	
}
