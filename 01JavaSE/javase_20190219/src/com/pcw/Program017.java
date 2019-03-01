package com.pcw;

public class Program017 {

	public static void main(String[] args) {
		//문제) 몫과 나머지 연산
		//거스름돈 계산 과정을 코드로 작성
		//예를 들어, 임시변수에 1670원이 있다고 가정
		//500, 100, 50, 10원짜리 동전밖에 없다
		
		//입력과정
		//변수 선언 및 초기값 할당
		int a = 1670;
		int m500 = 0, m100 = 0, m50 = 0, m10 = 0;
		
		//처리과정
		//-> 동전 갯수 연산 후 변수에 저장
		m500 = a/500;
		
		m100 = (a%500)/100;
		
		m50 = (a%500%100)/50;
		
		m10 = (a%500%100%50)/10;
		
		System.out.println(a);
		System.out.println(m500);
		System.out.println(m100);
		System.out.println(m50);
		System.out.println(m10);
		
		System.out.printf("거스름돈 : %d%n500원:%d, 100원:%d, 50원:%d, 10원:%d%n",a,m500,m100,m50,m10);
	}

}
