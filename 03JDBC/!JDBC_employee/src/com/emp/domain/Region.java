package com.emp.domain;

public class Region {
	private String regId;
	private String reg_name;
	private int count_;
	
	public Region() {
		
	}

	public Region(String regId, String reg_name, int count_) {
		this.regId = regId;
		this.reg_name = reg_name;
		this.count_ = count_;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getReg_name() {
		return reg_name;
	}

	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
	}

	public int getCount_() {
		return count_;
	}

	public void setCount_(int count_) {
		this.count_ = count_;
	}

	@Override
	public String toString() {
		return String.format("%s / %s", this.regId, this.reg_name);
	}

	
	
}
