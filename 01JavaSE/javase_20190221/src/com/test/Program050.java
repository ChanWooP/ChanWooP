package com.test;

public class Program050 {

	public static void main(String[] args) {
		//문제) 별 문자 출력
		//출력예)
		/*
	 	*
		  *
		    *
		      *
		        *
		          *
		            *
		              *
		                *
		                  *
		 */
		
		for(int i=0; i<10; ++i) {
			for(int j=0; j<i; ++j) {
				System.out.printf("  ");
			}
			System.out.println("*");
		}
	}

}
