package com.emp.domain;

public class Login {
	private String id_;
	private String pw_;
	
	public Login() {
		
	}
	
	public Login(String id_,String pw_) {
		this.id_ = id_;
		this.pw_ = pw_;
	}
	
	public String getId_() {
		return id_;
	}
	public void setId_(String id_) {
		this.id_ = id_;
	}
	public String getPw_() {
		return pw_;
	}
	public void setPw(String pw_) {
		this.pw_ = pw_;
	}
	
}	
