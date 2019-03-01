package com.test5;

public class CoffeeMachine {
	
	//프로퍼티 - 
	boolean cap = false;
	boolean moon = false;
	int water = 1000;
	
	//메소드 - 
	String pushCap() {
		if(cap == true) {
			return "캡슐이 들어있음";
		}else {
			cap = true;
			return "캡슐들어감";
		}
	}
	
	void moonOnOff() {
		if(moon == false) {
			moon = true; 
		}else {
			moon = false;
		}
	}
	
	String mdrop(int water1) {
		
		
		if(water < water1 )
		{
			return "물이 없습니다";
		}
		
		if(cap == true) {
			water -= water1;
			cap = false;
			return water1+"미리 커피가 나왔습니다.";
		}else {
			water -= water1;
			return "물만 나왔습니다.";
		}
		
		
			
	}
}
