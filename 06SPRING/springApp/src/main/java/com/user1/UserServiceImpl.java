package com.user1;

public class UserServiceImpl implements UserService {
	private String name;
	private String tel;
	private int age;
	
	public UserServiceImpl() {
		this.name="ÀÚ¹Ù";
		this.tel="010-0000-0000";
		this.age=20;
	}
	
	@Override
	public String message() {
		String s=name+" : "+tel+" : "+age; 
		return s;
	}
}
