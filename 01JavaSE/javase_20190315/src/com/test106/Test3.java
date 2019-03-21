package com.test106;

import java.io.File;
import java.io.IOException;

public class Test3 {
	public static void main(String[] args) {
	
		
		try {
			//디렉토리 생성
			File dir = new File("C://Test");
			System.out.println(dir.mkdir());
			
			//파일 생성
			File file = new File(dir,"test123.txt");
			System.out.println(file.createNewFile());

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
