package com.anno5;

import org.springframework.stereotype.Component;

@Component("userService")  // 아이디를 지정
public class UserServiceImpl implements UserService {
	private String name;
	private String tel;
	private int age;
	
	public UserServiceImpl() {
		name="안드로이드";
		tel="010-1111-1111";
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
