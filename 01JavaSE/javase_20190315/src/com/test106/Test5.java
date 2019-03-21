package com.test106;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Test5 {

	public static void main(String[] args) {
		//직렬화 가능 클래스인 경우 직렬화 가능
		List<Member> list = new ArrayList<Member>();
		
		try {
			//ser은 관습적으로 많이 쓰는 확장자
			FileInputStream fo = new FileInputStream("src/com/test106/test.ser");
			ObjectInputStream oo = new ObjectInputStream(fo);
			
			list = (List<Member>) oo.readObject();
			
			System.out.println(list.toString());
			
			System.out.println("역직렬화완료");
			
			oo.close();
			fo.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
