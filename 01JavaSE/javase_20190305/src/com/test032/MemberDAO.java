package com.test032;

public class MemberDAO {
	
	//Member 자료형을 가진 배열 저장소
	//힙이라는 공간에 Member 객체 10개까지 저장 가능한 공간 확보
	//스택이라는 공간에 배열의 참조주소를 저장한 members 변수
	private Member[] members = new Member[10];
	private int idx = 0; // 인덱스 변수
	
	public MemberDAO() {
		Member m = new Member();
		m.setMid("M001");
		m.setName("hong");
		m.setPhone("010-1111-1111");
		m.setEmail("asdf@naver.com");
		this.members[this.idx] = m;
		++this.idx;
	}
	
	public void add(Member m) {
		//외부에서 전달받은 Member 객체를 (배열)저장소에 저장
		this.members[this.idx] = m;
		//(배열)저장소의 인덱스 관리를 위한 +1 연산
		++this.idx;
	}
	
	//자료 저장소의 전체 자료 반환
	//->사본배열(얕은 복사 or 깊은복사)
	public Member[] list() {
		
		Member[] members_ = new Member[this.members.length];
		
		for(int a=0; a<this.members.length; ++a) {
			members_[a] = this.members[a]; //원본을 사본으로 
		}
		
		return members_;
	}
	
	public Member[] list(String key, String value) {
		return null;
	}
	
}
