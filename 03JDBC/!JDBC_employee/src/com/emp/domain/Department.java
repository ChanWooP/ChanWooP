package com.emp.domain;

public class Department {
	private String deptId;
	private String dept_name;
	private int count_;
	
	public Department() {
		
	}

	public Department(String deptId, String dept_name, int count_) {
		this.deptId = deptId;
		this.dept_name = dept_name;
		this.count_ = count_;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public int getCount_() {
		return count_;
	}

	public void setCount_(int count_) {
		this.count_ = count_;
	}

	@Override
	public String toString() {
		return String.format("%s / %s", this.deptId, this.dept_name);
	}
	
	
	
}
