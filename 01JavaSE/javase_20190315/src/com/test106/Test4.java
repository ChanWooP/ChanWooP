package com.test106;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Test4 {

	public static void main(String[] args) {
		
		//직렬화 가능 클래스인 경우 직렬화 가능
		List<Member> list = new ArrayList<Member>();
	
		list.add(new Member("hone1", "1"));
		list.add(new Member("hone2", "2"));
		list.add(new Member("hone3", "3"));
		list.add(new Member("hone4", "4"));
		
		System.out.println(list.toString());
		
		
		try {
			//ser은 관습적으로 많이 쓰는 확장자
			FileOutputStream fo = new FileOutputStream("src/com/test106/test.ser");
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			
			oo.writeObject(list);
			System.out.println("직렬화완료");
			
			oo.close();
			fo.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
