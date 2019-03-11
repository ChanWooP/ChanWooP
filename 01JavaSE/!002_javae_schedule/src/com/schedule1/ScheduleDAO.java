package com.schedule1;

public class ScheduleDAO {
	
	//배열 저장소 준비
	//->자동 확장 액션 추가
	private Schedule[] schedules = new Schedule[3];
	private int idx = 0;
	
	public void add(Schedule schedule) {
		schedules[idx++] = schedule;
	}
	
	public Schedule[] list(String key, String value) {
		Schedule[] temp = new Schedule[this.idx];
		int b = 0;
		
		for(int a=0; a<this.idx; ++a, ++b) {
			Schedule s = this.schedules[a];
			
			//전체일정
			if(key.equals("all")) temp[b] = s;
			
			//오늘일정
			
			//특정일
			
			//특정월
			
			//특정단어포함
			
		}
		return temp;
	}
	
	public void sort_(Schedule[] list) {
		
	}
	
}
