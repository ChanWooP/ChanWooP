package com.test106;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		try(FileInputStream fi = new FileInputStream("src/com/test106/test.txt")) {
			int data = 0;
			while((data = fi.read()) != -1) {
				System.out.println((char)data);
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
