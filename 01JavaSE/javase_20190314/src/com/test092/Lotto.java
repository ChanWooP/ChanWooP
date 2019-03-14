package com.test092;

import java.util.List;

public class Lotto {
	
	//판매소 고유번호(L001, L002, ... L999)
	//로또번호 세트(5개번호 1세트)
	
	private String lid;
	private List<Integer> lotto;
	
	public Lotto(String lid, List<Integer> lotto) {
		super();
		this.lid = lid;
		this.lotto = lotto;
	}
	
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public List<Integer> getLotto() {
		return lotto;
	}
	public void setLotto(List<Integer> lotto) {
		this.lotto = lotto;
	}
	@Override
	public String toString() {
		return "lotto";
	}
	
}
