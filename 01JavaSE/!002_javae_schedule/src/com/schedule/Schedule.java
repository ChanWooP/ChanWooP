package com.schedule;

public class Schedule {
	
	private String sid;
	private String wdate;
	private String content;
	
	public Schedule() {
		super();
	}

	public Schedule(String wdate, String content) {
		super();
		this.wdate = wdate;
		this.content = content;
	}
	
	public Schedule(String sid, String wdate, String content) {
		super();
		this.sid = sid;
		this.wdate = wdate;
		this.content = content;
	}
	
	public String getMid() {
		return sid;
	}
	
	public void setMid(String sid) {
		this.sid = sid;
	}
	
	public String getWdate() {
		return wdate;
	}
	
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	
	public String getMemo() {
		return content;
	}
	
	public void setMemo(String content) {
		this.content = content;
	}
	
	//toString() 오버라이딩
	@Override 
	public String toString() {
		//객체의 상태 기본 문자열 -> 패키지.클래스@해쉬코드(기존)
		//객체의 상태값을 내가 원하는 문자열로 반환(오버라이드)
		
		//S012 / 2018=03-08 / 일정관리 프로그램 작성
		return String.format("%s / %s / %s"
				, this.sid, this.wdate, this.content);
	}

}
