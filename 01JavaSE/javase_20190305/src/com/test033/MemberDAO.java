package com.test033;

public class MemberDAO {
	private Member[] members = new Member[10];
	private int idx = 0; // 인덱스 변수
	
	public void add(Member m) {
		this.members[this.idx] = m;
		++this.idx;
	}

	public Member[] list() {
		
		Member[] members_ = new Member[this.members.length];
		
		for(int a=0; a<this.members.length; ++a) {
			members_[a] = this.members[a];  
		}
		
		return members_;
	}
	
	public Member[] list(String key, String value) {
		return null;
	}
	
	public int getIdx() {
		return idx;
	}
}
