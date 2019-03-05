package com.test032;

public class Member {
	//프로퍼티 구성
	//회원번호(M001), 이름, 전화번호, 이메일, ...
	//유니크한 자료 저장을 할 수 있는 항목 구성
	private String mid;
	private String name, phone, email;

	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
