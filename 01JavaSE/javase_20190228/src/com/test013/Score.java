package com.test013;

public class Score {
	//구성항목 : 학번 과목1 과목2 과목3
	//자료형 : 학번은 String / 과목1,2,3 int
	//샘플자료 : 
	//"S001", 100, 100, 100
	//"S002", 100, 100, 100
	
	private String stuNum;
	private int sub1, sub2, sub3;
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
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
	
	
}
