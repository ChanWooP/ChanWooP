package com.test064;

import java.util.Arrays;

public class Member {
	private String name, phone;

	public Member() {
		
	}
	
	public Member(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public boolean equals(Object obj) {
		Member otherMember = (Member)obj;
		boolean result = false;
		if(this.name.equals(otherMember.getName())
				&& this.phone.equals(otherMember.getPhone())) {
			result = true;
		}
		return result;
			
	}

	@Override
	public String toString() {
		return String.format("%s, %s", this.name, this.phone);
	}
	
	
}
