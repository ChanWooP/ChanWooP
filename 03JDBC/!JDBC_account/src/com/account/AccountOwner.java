package com.account;

public class AccountOwner {
	// 자료 항목에 따른 필드 구성
	// ->계좌주고유번호, 이름, 전화번호
	private String accountOwnerId;
	private String accountOwnerName;
	private String accountOwnerPhone;
	
	// 생성자 -> 기본 생성자, 매개변수 있는 생성자
	public AccountOwner() {
		
	}
	
	// 계좌주 생성용 생성자
	public AccountOwner(String accountOwnerId, String accountOwnerName
			, String accountOwnerPhone) {
		this.accountOwnerId = accountOwnerId;
		this.accountOwnerName = accountOwnerName;
		this.accountOwnerPhone = accountOwnerPhone;
	}

	//getter, setter
	public String getAccountOwnerId() {
		return accountOwnerId;
	}

	public void setAccountOwnerId(String accountOwnerId) {
		this.accountOwnerId = accountOwnerId;
	}

	public String getAccountOwnerName() {
		return accountOwnerName;
	}

	public void setAccountOwnerName(String accountOwnerName) {
		this.accountOwnerName = accountOwnerName;
	}

	public String getAccountOwnerPhone() {
		return accountOwnerPhone;
	}

	public void setAccountOwnerPhone(String accountOwnerPhone) {
		this.accountOwnerPhone = accountOwnerPhone;
	}
	
	
	
}
