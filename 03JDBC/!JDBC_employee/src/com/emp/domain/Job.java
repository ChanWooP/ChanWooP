package com.emp.domain;

public class Job {
	private String jobId;
	private String job_name;
	private int min_basicPay;
	private int count_;
	
	public Job() {
		
	}

	public Job(String jobId, String job_name, int min_basicPay, int count_) {
		this.jobId = jobId;
		this.job_name = job_name;
		this.min_basicPay = min_basicPay;
		this.count_ = count_;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public int getMin_basicPay() {
		return min_basicPay;
	}

	public void setMin_basicPay(int min_basicPay) {
		this.min_basicPay = min_basicPay;
	}

	public int getCount_() {
		return count_;
	}

	public void setCount_(int count_) {
		this.count_ = count_;
	}

	@Override
	public String toString() {
		return String.format("%s / %s / %d", this.jobId, this.job_name, this.min_basicPay);
	}
	
	
	
}
