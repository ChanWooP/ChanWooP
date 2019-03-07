package com.test043;

public class Score {
	
	private String mid;
	private String name;
	private int sub1;
	private int sub2;
	private int sub3;
	private int rank_ = 1;
	
	public Score() {}
	
	public Score(String mid, String name, int sub1, int sub2, int sub3) {
		this.mid = mid;
		this.name = name;
		this.sub1 = sub1;
		this.sub2 = sub2;
		this.sub3 = sub3;
	}
	
	public int getTotal() {
		return this.sub1 + this.sub2 + this.sub3;
	}

	public double getAvg_() {
		return (this.sub1 + this.sub2 + this.sub3) / 3.0;
	}

	public int getRank_() {
		return this.rank_;
	}
	public void setRank_(int rank_) {
		this.rank_ = rank_;
	}
	
	public String getMid() {
		return this.mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSub1() {
		return this.sub1;
	}
	public void setSub1(int sub1) {
		this.sub1 = sub1;
	}
	public int getSub2() {
		return this.sub2;
	}
	public void setSub2(int sub2) {
		this.sub2 = sub2;
	}
	public int getSub3() {
		return this.sub3;
	}
	public void setSub3(int sub3) {
		this.sub3 = sub3;
	}
	
}
