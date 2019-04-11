package com.score;

public class Score {
	
	private String sid_;
	private String name_;
	private int sub1;
	private int sub2;
	private int sub3;
	private int sum_;
	private double avg_;
	private int rank_;
	
	
	public Score() {
	
	}
	
	public Score(String sid_, String name_, int sub1, int sub2, int sub3) {
		super();
		this.sid_ = sid_;
		this.name_ = name_;
		this.sub1 = sub1;
		this.sub2 = sub2;
		this.sub3 = sub3;
	}

	public Score(String sid_, String name_, int sub1, int sub2, int sub3, int sum_, double avg_, int rank_) {
		super();
		this.sid_ = sid_;
		this.name_ = name_;
		this.sub1 = sub1;
		this.sub2 = sub2;
		this.sub3 = sub3;
		this.sum_ = sum_;
		this.avg_ = avg_;
		this.rank_ = rank_;
	}

	public String getSid_() {
		return sid_;
	}

	public void setSid_(String sid_) {
		this.sid_ = sid_;
	}

	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	public int getSub1() {
		return sub1;
	}

	public void setSub1(int sub1) {
		this.sub1 = sub1;
	}

	public int getSub2() {
		return sub2;
	}

	public void setSub2(int sub2) {
		this.sub2 = sub2;
	}

	public int getSub3() {
		return sub3;
	}

	public void setSub3(int sub3) {
		this.sub3 = sub3;
	}

	public int getSum_() {
		return sum_;
	}

	public void setSum_(int sum_) {
		this.sum_ = sum_;
	}

	public double getAvg_() {
		return avg_;
	}

	public void setAvg_(double avg_) {
		this.avg_ = avg_;
	}

	public int getRank_() {
		return rank_;
	}

	public void setRank_(int rank_) {
		this.rank_ = rank_;
	}

	@Override
	public String toString() {
		return String.format("%s / %s / %d / %d / %d / %d / %.2f / %d", this.sid_, this.name_, this.sub1, this.sub2, this.sub3, this.sum_, this.avg_, this.rank_);
	}
	
}
