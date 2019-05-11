package com.subject;

public class Subject_ {
	
	private String subId;
	private String subName;
	private String subDate_;
	private String subNum;
	private int subCount;
	
	//출력용
	public Subject_(String subId, String subName, String subDate_, String subNum, int subCount) {
		this.subId = subId;
		this.subName = subName;
		this.subDate_ = subDate_;
		this.subNum = subNum;
		this.subCount = subCount;
	}
	
	//입력용
	public Subject_(String subId, String subName, String subDate_, String subNum) {
		this.subId = subId;
		this.subName = subName;
		this.subDate_ = subDate_;
		this.subNum = subNum;
	}
	
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getSubDate_() {
		return subDate_;
	}
	public void setSubDate_(String subDate_) {
		this.subDate_ = subDate_;
	}
	public String getSubNum() {
		return subNum;
	}
	public void setSubNum(String subNum) {
		this.subNum = subNum;
	}
	public int getSubCount() {
		return subCount;
	}
	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}
	
}
