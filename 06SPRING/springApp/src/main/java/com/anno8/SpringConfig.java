package com.anno8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	// ���̸��� ������� ������ ���̸��� �޼ҵ��(userDevice)�� �ȴ�.
	@Bean(name="userBean", initMethod="init", destroyMethod="destroy")
	public UserBean userDevice() {
		return new UserBean();
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	
}
