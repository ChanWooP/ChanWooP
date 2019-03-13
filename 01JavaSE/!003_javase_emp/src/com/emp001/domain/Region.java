package com.emp001.domain;

//자료형 클래스 - 지역정보
//출력시 지역번호 기준 정렬되도록 지원 -> Comparable 인터페이스 구현
public class Region{

	//지역번호, 지역명
	//지역번호(REG01, REG02, ...REG99)
	private String regNum;
	private String regName;
	
	//생성자
	public Region() {
		
	}
	
	public Region(String regNum, String regName) {
		this.regNum = regNum;
		this.regName = regName;
	}
	
	//getter, setter
	public String getRegNum() {
		return this.regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	public String getRegName() {
		return this.regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}
	
	//toString()
	@Override
	public String toString() {
		return String.format("%s - %s", this.regNum, this.regName);
	}
	
}
