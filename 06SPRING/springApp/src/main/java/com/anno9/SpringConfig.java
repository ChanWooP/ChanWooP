package com.anno9;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	// ���̸��� ������� ������ ���̸��� �޼ҵ��(userDevice)�� �ȴ�.
	@Bean(name="userBean")
	public UserBean userDevice() {
		return new UserBean();
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	
}
