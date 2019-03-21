package com.test106;

import java.io.ByteArrayInputStream;

public class Main {

	public static void main(String[] args) {
		byte[] inSrc = {1,2,3,4,5};
		ByteArrayInputStream input = null;
		input = new ByteArrayInputStream(inSrc);
		
		int date = 0;
		
		//더 이상 읽을 내용이 없으면 -1을 반환한다
		//read할 때마다 1바이트씩 읽는다
		while((date = input.read()) != -1) {
			System.out.println(date);
		}
	}

}
