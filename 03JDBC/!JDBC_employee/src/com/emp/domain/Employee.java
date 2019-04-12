package com.emp.domain;

public class Employee {
	private String empId, name_, ssn, hiredate, phone;
	private String regId, reg_name, deptId, dept_name, jobId, job_title;
	private int basicPay, extrapay, total;
	
	public Employee() {
		
	}

	public Employee(String empId,String name_,String ssn,String hiredate
			,String phone,String regId,String deptId,String jobId, int basicpay,int extrapay) {
		this.empId = empId;
		this.name_ = name_;
		this.ssn = ssn; 
		this.hiredate = hiredate;
		this.phone = phone;
		this.regId = regId;
		this.deptId = deptId;
		this.jobId  = jobId;
		this.basicPay = basicpay;
		this.extrapay = extrapay;
	}
	
	public Employee(String empId, String name_, String ssn, String hiredate, String phone, String regId,
			String reg_name, String deptId, String dept_name, String jobId, String job_title, int basicPay,
			int extrapay, int total) {
		super();
		this.empId = empId;
		this.name_ = name_;
		this.ssn = ssn;
		this.hiredate = hiredate;
		this.phone = phone;
		this.regId = regId;
		this.reg_name = reg_name;
		this.deptId = deptId;
		this.dept_name = dept_name;
		this.jobId = jobId;
		this.job_title = job_title;
		this.basicPay = basicPay;
		this.extrapay = extrapay;
		this.total = total;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredaoe(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getReg_name() {
		return reg_name;
	}

	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
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

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public int getBasicPay() {
		return basicPay;
	}

	public void setBasicPay(int basicPay) {
		this.basicPay = basicPay;
	}

	public int getExtrapay() {
		return extrapay;
	}

	public void setExtrapay(int extrapay) {
		this.extrapay = extrapay;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return String.format("%s / %s / %s / %s / %s / %s / %s / %s / %d / %d / %d"
				, this.empId, this.name_, this.ssn, this.hiredate, this.phone
				, this.reg_name, this.dept_name, this.job_title
				, this.basicPay, this.extrapay, this.total);
	}
	
	
}
