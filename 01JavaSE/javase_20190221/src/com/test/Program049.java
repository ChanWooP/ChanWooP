package com.test;

public class Program049 {

	public static void main(String[] args) {
		//숫자 피라미드 출력
		//출력예)
		/*
		9 8 7 6 5 4 3 2 1 0 
		8 7 6 5 4 3 2 1 0 
		7 6 5 4 3 2 1 0 
		6 5 4 3 2 1 0 
		5 4 3 2 1 0 
		4 3 2 1 0 
		3 2 1 0 
		2 1 0 
		1 0 
		0 
		 */
		for(int i = 9; i>=0; --i) {
			for(int j = i; j>=0; --j) {
				System.out.printf("%d ", j);
			}
			System.out.println();
		}
	}

}
