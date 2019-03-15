package com.emp002.domain;

//자료형 클래스 - 직위정보
public class Position {
	//직위번호, 직위명, 최소기본급, 보너스
	//직위번호(POS01, POS02, ... POS99)  
	private String posNum;
	private String posName;
	private int posMoney;
	private int extraMoney;
	
	public Position() {
		
	}
	
	public Position(String posNum, String posName, int posMoney, int extraMoney) {
		this.posNum = posNum;
		this.posName = posName;
		this.posMoney = posMoney;
		this.extraMoney = extraMoney;
	}
	
	public String getPosNum() {
		return this.posNum;
	}

	public void setPosNum(String posNum) {
		this.posNum = posNum;
	}

	public String getPosName() {
		return this.posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public int getPosMoney() {
		return posMoney;
	}

	public void setPosMoney(int posMoney) {
		this.posMoney = posMoney;
	}
	

	public int getExtraMoney() {
		return extraMoney;
	}

	public void setExtraMoney(int extraMoney) {
		this.extraMoney = extraMoney;
	}

	//toString()
	@Override
	public String toString() {
		return String.format("%s - %s - %d - %d", this.posNum, this.posName, this.posMoney, this.extraMoney);
	}
}
