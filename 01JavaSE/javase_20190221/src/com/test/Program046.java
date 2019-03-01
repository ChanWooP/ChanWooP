/*
 * 프로그램명 : 
 * 작성자 : 박찬우
 * 작성일 : 20190221
 * 프로그램기능 : 
 */

package com.test;

public class Program046 {

	public static void main(String[] args) {
		//숫자 피라미드 출력
		//출력예)
		/*
		 0 1 2 3 4 5 6 7 8 9 
		 0 1 2 3 4 5 6 7 8 9 
		 0 1 2 3 4 5 6 7 8 9 
		 0 1 2 3 4 5 6 7 8 9 
		 0 1 2 3 4 5 6 7 8 9 
		 0 1 2 3 4 5 6 7 8 9 
		 0 1 2 3 4 5 6 7 8 9 
		 0 1 2 3 4 5 6 7 8 9 
		 0 1 2 3 4 5 6 7 8 9 
		 0 1 2 3 4 5 6 7 8 9 
		 */
		
		for(int i = 0; i<=10; ++i) {
			for(int j = 0; j<10; ++j) {
				System.out.printf("%d ", j);
			}
			System.out.println();
		}
	}

}
