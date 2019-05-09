package com.guestbook;

public class Guestbook {
	private int ssn;
	private String name_;
	private String title;
	private String sdate;
	private String ipaddress;
	private int blind;
	private String pw;
	private String contents;
	
	public Guestbook(int ssn, String name_, String sdate, String ipaddress, int blind,
			String contents) {
		this.ssn = ssn;
		this.name_ = name_;
		this.sdate = sdate;
		this.ipaddress = ipaddress;
		this.blind = blind;
		this.contents = contents;
	}
	
	public Guestbook(String name_, String ipaddress, String pw, String contents) {

		this.name_ = name_;
		this.ipaddress = ipaddress;
		this.pw = pw;
		this.contents = contents;
	}
	
	public Guestbook(int ssn, String name_, String title, String sdate, String ipaddress, int blind, String pw,
			String contents) {
		this.ssn = ssn;
		this.name_ = name_;
		this.title = title;
		this.sdate = sdate;
		this.ipaddress = ipaddress;
		this.blind = blind;
		this.pw = pw;
		this.contents = contents;
	}

	public Guestbook(int ssn, String name_, String sdate, String ipaddress, int blind, String pw,
			String contents) {
		this.ssn = ssn;
		this.name_ = name_;
		this.sdate = sdate;
		this.ipaddress = ipaddress;
		this.blind = blind;
		this.pw = pw;
		this.contents = contents;
	}


	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public String getName_() {
		return name_;
	}
	public void setName_(String name_) {
		this.name_ = name_;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public int getBlind() {
		return blind;
	}
	public void setBlind(int blind) {
		this.blind = blind;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
}
