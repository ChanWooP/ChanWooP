package com.anno10;

import org.springframework.stereotype.Service;

@Service("userService") // 객체생성
public class UserServiceImpl implements UserService {
	private String name;
	private String tel;
	private int age;
	
	public UserServiceImpl() {
		name="스프링";
		tel="010-3333-3333";
		age=20;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String message() {
		String s="이름:"+name+", 전화:"+tel+", 나이:"+age;
		return s;
	}

}
