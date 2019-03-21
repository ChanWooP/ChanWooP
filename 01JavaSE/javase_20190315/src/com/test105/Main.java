package com.test105;

import java.io.FileInputStream;

public class Main {

	public static void main(String[] args) {
		
		//with resources 구문을 이용하면 객체 소멸 과정을 자동 실행한다
		try (FileInputStream fs = new FileInputStream("src/com/test105/test.txt")){
			
			System.out.println((char)fs.read());
			System.out.println((char)fs.read());
			System.out.println((char)fs.read());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
