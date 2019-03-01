/*
 * 프로그램명 : 
 * 작성자 : 
 * 작성일 : 
 */

package com.test;

class Remotecontrol{
	boolean power = false; // 프로퍼티(상태)
	int channel = 0;

	void powerOn() { // 메소드(기능)
		power = true;
	}
	
	void powerOff() { // 메소드(기능)
		power = false;
	}

    void channelPush(int number) { //메소드에 매게변수를 받을 수 있음
        channel = number;
    }
}

public class Program120 {

	public static void main(String[] args) {
        // 인스턴스 생성
		Remotecontrol r1 = new Remotecontrol();
		
        //인스턴스 확인
		//패키지명.클래스명@해쉬코드
        System.out.println(r1); 
        
        //인스턴스의 메소드 호출
		System.out.println(r1.power); // 결과 : false
		r1.powerOn();
		System.out.println(r1.power); // 결과 : true
		r1.powerOff();
		System.out.println(r1.power); // 결과 : false

        System.out.println(r1.channel); // 결과 : 0
        r1.channelPush(100);
        System.out.println(r1.channel); // 결과 : 100
       
	}
}