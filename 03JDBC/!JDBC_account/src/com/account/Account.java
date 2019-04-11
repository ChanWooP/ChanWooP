package com.account;

public class Account {

	// 자료 항목에 따른 필드 구성
	// ->계좌번호, 계좌주고유번호, 현재잔액, 계좌생성일, 비밀번호, 마지막거래일
	private String accountId;
	private String accountOwnerId;
	private int balance;
	private String accountCreateDate;
	private String pw;
	private String lastUpdateDate;

	// 생성자 -> 기본 생성자, 매개변수 있는 생성자
	public Account() {
		
	}
	
	//출금용 생성자
	public Account(String accountId, String pw) {
		this.accountId = accountId;
		this.pw = pw;
	}

	//계좌생성용 생성자
	public Account(String accountId, String accountOwnerId, String pw, int balance) {
		this.accountId = accountId;
		this.accountOwnerId = accountOwnerId;
		this.pw = pw;
		this.balance = balance;
	}
	
	
	//그냥 만듦
	public Account(String accountId, String accountOwnerId, int balance
			, String accountCreateDate, String pw, String lastUpdateDate) {
		this.accountId = accountId;
		this.accountOwnerId = accountOwnerId;
		this.balance = balance;
		this.accountCreateDate = accountCreateDate;
		this.pw = pw;
		this.lastUpdateDate = lastUpdateDate;
	}
	
	//getter, setter
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountOwnerId() {
		return accountOwnerId;
	}

	public void setAccountOwnerId(String accountOwnerId) {
		this.accountOwnerId = accountOwnerId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAccountCreateDate() {
		return accountCreateDate;
	}

	public void setAccountCreateDate(String accountCreateDate) {
		this.accountCreateDate = accountCreateDate;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
}
