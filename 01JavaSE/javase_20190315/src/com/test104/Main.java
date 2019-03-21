package com.test104;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Sample s = new Sample();
		
		try {
			s.method();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}finally {
			
		}
	}

}
