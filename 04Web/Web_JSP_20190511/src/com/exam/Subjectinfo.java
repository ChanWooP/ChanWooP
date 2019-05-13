package com.exam;

public class Subjectinfo {
	
	private String sid_, subjectid_, sname_, sdate_;
	private int scount_, examcount_;
	private String status_;
	
	public Subjectinfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Subjectinfo(String subjectid_, String sname_, String sdate_, int scount_) {
		super();
		this.subjectid_ = subjectid_;
		this.sname_ = sname_;
		this.sdate_ = sdate_;
		this.scount_ = scount_;
	}

	public Subjectinfo(String sid_, String subjectid_, String sname_, String sdate_, int scount_, int examcount_,
			String status_) {
		super();
		this.sid_ = sid_;
		this.subjectid_ = subjectid_;
		this.sname_ = sname_;
		this.sdate_ = sdate_;
		this.scount_ = scount_;
		this.examcount_ = examcount_;
		this.status_ = status_;
	}

	public String getSid_() {
		return sid_;
	}

	public void setSid_(String sid_) {
		this.sid_ = sid_;
	}

	public String getSubjectid_() {
		return subjectid_;
	}

	public void setSubjectid_(String subjectid_) {
		this.subjectid_ = subjectid_;
	}

	public String getSname_() {
		return sname_;
	}

	public void setSname_(String sname_) {
		this.sname_ = sname_;
	}

	public String getSdate_() {
		return sdate_;
	}

	public void setSdate_(String sdate_) {
		this.sdate_ = sdate_;
	}

	public int getScount_() {
		return scount_;
	}

	public void setScount_(int scount_) {
		this.scount_ = scount_;
	}

	public int getExamcount_() {
		return examcount_;
	}

	public void setExamcount_(int examcount_) {
		this.examcount_ = examcount_;
	}

	public String getStatus_() {
		return status_;
	}

	public void setStatus_(String status_) {
		this.status_ = status_;
	}
	
	
	

}


