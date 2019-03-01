package com.test;

public class Program047 {

	public static void main(String[] args) {
		//숫자 피라미드 출력
		//출력예)
		/*
		0 
		0 1 
		0 1 2 
		0 1 2 3 
		0 1 2 3 4 
		0 1 2 3 4 5 
		0 1 2 3 4 5 6 
		0 1 2 3 4 5 6 7 
		0 1 2 3 4 5 6 7 8 
		0 1 2 3 4 5 6 7 8 9 
		 */
		
		for(int i = 1; i<=10; ++i) {
			for(int j = 0; j<i; ++j) {
				System.out.printf("%d ", j);
			}
			System.out.println();
		}
	}

}
