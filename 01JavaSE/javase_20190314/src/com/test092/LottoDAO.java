package com.test092;

import java.util.ArrayList;
import java.util.List;

public class LottoDAO {

	//로또 번호 저장소
	private List<Lotto> list = new ArrayList<Lotto>();
	
	//로또 번호 저장
	public void add(Lotto lotto) {
		//저장소 크기, 인덱스 자동관리
		this.list.add(lotto);
	}
	
	//로또 번호 출력
	public List<Lotto> list(){
		List<Lotto> temp = new ArrayList<Lotto>();
		 for(Lotto l : this.list) {
			 temp.add(l);
		 }
		 return temp;
	}
	
	//로또 번호 검색
	public List<Lotto> list(int key, List<Integer> value){
		List<Lotto> temp = new ArrayList<Lotto>();
		
		for(Lotto l : this.list) {
			List<Integer> lotto = l.getLotto();
			int count = this.lottoCheck(lotto, value);
			if(count == key) {
				temp.add(l);
			}
		}
		return temp;
	}
	
	private int lottoCheck(List<Integer> l1, List<Integer> l2) {
		int count = 0;
		for (int i : l1) {
			for (int j : l2) {
				if (i == j) ++count;
			}
		}
		return count;
	}
}
