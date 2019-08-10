package com.user1;

public class UserServiceImpl2 implements UserService {
	private String name;
	private String tel;
	private int age;
	
	public UserServiceImpl2(String name, String tel, int age) {
		this.name=name;
		this.tel=tel;
		this.age=age;
	}
	
	@Override
	public String message() {
		String s=name+", "+tel+", "+age; 
		return s;
	}
}
