package com.member;

public class Member {
	//맴버구성
	//회원번호, 이름, 전화번호, 이메일
	//오라클 자료형 NUMBER -> 자바 자료형 int, double
	//오라클 자료형 VARCHAR2, NVARCHAR2 -> 자바 자료형 String
	//오라클 자료형 DATE -> 자바 자료형 String
	private String mid, name_, phone, email;
	
	public Member() {
		
	}
	
	public Member(String mid, String name_,String phone, String email) {
		this.mid = mid;
		this.name_ = name_;
		this.phone = phone;
		this.email = email;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return mid + "/" + name_ + "/" + phone + "/" + email;
	}
	
	
}
