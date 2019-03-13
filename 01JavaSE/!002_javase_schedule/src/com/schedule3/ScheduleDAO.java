package com.schedule3;

import java.util.Arrays;
import java.util.Comparator;

public class ScheduleDAO {
	
	//배열 저장소 준비
	//->자동 확장 액션 추가
	private Schedule[] schedules = new Schedule[3];
	private int idx = 0;
	
	public void add(Schedule schedule) {
		schedules[idx] = schedule;
		++idx;
	}
	
	public String generateSid() {
		String result = null;
		int temp = this.idx + 1;
		
		if(temp <= this.schedules.length) {
			result = String.format("S%03d", temp);
		}
		
		return result;
		
	}
	
	public Schedule[] list(String key, String value) {
		Schedule[] temp = new Schedule[this.idx];
		
        //for(int a=0; a<this.idx; ++a) {
        //	for(int b=a; b<this.idx; ++b) {
        //		if(this.schedules[a].getWdate().compareTo(this.schedules[b].getWdate())>0) {
        //			Schedule s = this.schedules[a];
        //			this.schedules[a] = this.schedules[b];
        //			this.schedules[b] = s;
        //		}
        //	}
        //}
		
		sort();
		
		int b = 0;
		for(int a=0; a<this.idx; ++a, ++b) {
			Schedule s = this.schedules[a];
			
			
			
			//전체일정
			if(key.equals("all")) {
				temp[b] = s;
			}
			//오늘일정
			else if(key.equals("today") && this.schedules[a].getWdate().equals(value)) {
				temp[b] = s;
			}
			//특정일
			else if(key.equals("getday") && this.schedules[a].getWdate().equals(value)) {
				temp[b] = s;
			}
			//특정월
			else if(key.equals("getmonth") && this.schedules[a].getWdate().contains(value)) {
				temp[b] = s;
			}
			//특정단어포함
			else if(key.equals("getdan") && this.schedules[a].getMemo().contains(value)) {
				temp[b] = s;
			}
			
		}
		return temp;
	}
	
	private void sort() {
		Arrays.sort(this.schedules, new Comparator<Schedule>() {
			public int compare(Schedule s1, Schedule s2) {
				if(s1 == null || s2 == null) return 0;
				return s1.getWdate().compareTo(s2.getWdate());
			}
		});
	}
	
}
