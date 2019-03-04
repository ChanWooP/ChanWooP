package com.test020;

public class Sample {
	//출력 예)
	//1980 -> 올해(2019) 기준으로 xx살입니다.
	int age;
	int toyear = 2019;
	
	void age(int year) {
		age = (toyear - year) +1;
		System.out.printf("%d년생은 올해(%d) 기준으로 %d살입니다.",year ,toyear, age);
	}
}
