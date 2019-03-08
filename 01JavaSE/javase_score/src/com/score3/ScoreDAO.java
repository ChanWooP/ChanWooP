package com.score3;

public class ScoreDAO {
	private Score[] members = new Score[3];
	private int idx = 0;
	
	//성적추가 메소드
	public void add(Score s) {
		members[idx++] = s;
	}
	
	//성적출력 메소드
	public Score[] list() {
		rank_();
		
		Score[] members = this.members;
		
		return members;
	}
	
	//성적검색 메소드
	public Score[] list(String key, String value) {
		rank_();
		int idx = 0;
		int count = 0;

		Score[] remembers = null; 
		
		for(int a=0; a<this.idx; ++a) {
			if(key == "mid") {
				if(members[a].getMid().equals(value)) {
					++count;
				}
			}else if(key == "name") {
				if(members[a].getName().contains(value)) {
					++count;
				}
			}
		}
		
		remembers = new Score[count];
		
		for(int a=0; a<this.idx; ++a) {
			if(key == "mid") {
				if(members[a].getMid().equals(value)) {
					remembers[idx++] = members[a];
				}
			}else if(key == "name") {
				if(members[a].getName().contains(value)) {
					remembers[idx++] = members[a];
				}
			}
		}
		return remembers;
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
	
}
