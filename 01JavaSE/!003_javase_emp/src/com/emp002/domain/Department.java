package com.emp002.domain;

//자료형 클래스 - 부서정보
public class Department {
	//부서번호, 부서명
	//지역번호(DEPT01, DEPT02, ... DEPT99)
	private String deptNum;
	private String deptName;
	
	public Department() {
		
	}
	
	public Department(String deptNum, String deptName) {
		super();
		this.deptNum = deptNum;
		this.deptName = deptName;
	}
	
	public String getDeptNum() {
		return this.deptNum;
	}
	public void setDeptNum(String deptNum) {
		this.deptNum = deptNum;
	}
	public String getDeptName() {
		return this.deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	//toString()
	@Override
	public String toString() {
		return String.format("%s - %s", this.deptNum, this.deptName);
	}
	
}
