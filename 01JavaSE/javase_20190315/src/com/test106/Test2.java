package com.test106;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test2 {

	public static void main(String[] args) {
		Scanner sc = null;
		
		try {
			sc = new Scanner(new File("src/com/test106/test.txt"));
			
			//읽을 만한 문장이 남았는지 확인
			while(sc.hasNextLine()) {
				String s = sc.nextLine();
				System.out.println(s);
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
		
		
		
	}

}
