package com.anno4;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserServiceImpl implements UserService {
	private String name;
	private String tel;
	private int age;
	
	@PostConstruct  // 생성자 호출 후
	public void init() {
		System.out.println("init...");
	}
	
	@PreDestroy // 객체가 소멸되기 직전
	public void close() {
		System.out.println("close...");
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
