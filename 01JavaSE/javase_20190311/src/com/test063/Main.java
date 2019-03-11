package com.test063;

public class Main {

	public static void main(String[] args) {
		Member m = new Member("s","s");
		Member m2 = new Member("s","s");
		
		//객체의 상태 정보 출력
		//패키저명.클래스명@해쉬코드
		System.out.println(m.toString());
		System.out.println(m2.toString());
		
		//두 객체 간의 참조주소 비교
		System.out.println(m.equals(m2));
	}

}
