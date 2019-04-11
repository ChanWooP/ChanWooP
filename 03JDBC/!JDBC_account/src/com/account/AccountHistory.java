package com.account;

public class AccountHistory {
	// 자료 항목에 따른 필드 구성
	// ->계좌번호, 입출금액, 입출금일, 입출금구분, 잔액
	private String accountId;
	private int money;
	private String inoutdate;
	private String gubun;
	private int balance;

	// 생성자 -> 기본 생성자, 매개변수 있는 생성자
	public AccountHistory() {
		
	}
	
	//입금용 생성자
	public AccountHistory(String accountId, int money, int balance) {
		this.accountId = accountId;
		this.money = money;
		this.balance = balance;
	}

	//출력용 생성자
	public AccountHistory(String accountId, int money, String inoutdate
			, String gubun, int balance) {
		this.accountId = accountId;
		this.money = money;
		this.inoutdate = inoutdate;
		this.gubun = gubun;
		this.balance = balance;
	}
	
	//getter, setter
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getInoutdate() {
		return inoutdate;
	}

	public void setInoutdate(String inoutdate) {
		this.inoutdate = inoutdate;
	}

	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	//toString()
	@Override
	public String toString() {
		return String.format("%s / %d / %s / %s / %d"
				, this.accountId, this.money, this.inoutdate, this.gubun, this.balance);
	}

}
