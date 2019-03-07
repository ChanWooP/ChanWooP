package com.test054;

public class ScoreDAO {
	private Score[] members = new Score[10];
	private int idx = 0;
	
	public ScoreDAO() {
		Score member = new Score("G001","홍길동",100, 100, 100);
		this.members[idx] = member;
		++idx;
		
		Score member2 = new Score("G002","일지매",90, 100, 100);
		this.members[idx] = member2;
		++idx;
		
		Score member3 = new Score("G003","박무봉",80, 100, 100);
		this.members[idx] = member3;
		++idx;
	}
	
	//성적추가 메소드
	public void add() {

	
	}
	
	//성적출력 메소드
	public Score[] list() {
		rank_();
		
		Score[] members = this.members;
		
		return members;
	}
	
	//성적검색 메소드
	public Score[] list(String key, String value) {
		return this.members;
	}
	
	//석차입력 메소드
	private void rank_() {
		
		for(Score m : members) {
			if(m == null) break;
			int rank_ = 1;
			for(Score m2 : members) {
				if(m2 == null) break;
				if(m.getTotal() < m2.getTotal()) {
					
					++rank_;
				}
			}
			m.setRank_(rank_);
		}
	}
	
	public boolean fullIdx() {
		return this.idx == this.members.length;
	}
	
	public int getIdx() {
		return this.idx;
	}
}
