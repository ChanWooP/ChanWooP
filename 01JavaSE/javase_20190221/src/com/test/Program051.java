package com.test;

public class Program051 {

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
		/*
		for(int i=0; i<10; ++i) {
			for(int j=0; j<i; ++j) {
				System.out.printf("  ");
			}
			System.out.println("*");
			if(i==10-1) {
				for(int b=i-1; b>=0; --b) {
					for(int a=b; a>0; --a) {
						System.out.printf("  ");
					}
					System.out.println("*");
				}
			}
		}
		*/
		
		int tmp = 0;
		int num = 11;
		
		if(num%2 == 0) {
			System.out.println("짝수는 사용 불가능");
			return;
		}
		
		for(int i=0; i<num; ++i) {
			if(i<=(num/2)-1) {
				for(int j=0; j<i; ++j) {
					System.out.printf("  ");
				}
				tmp = tmp + 1;
			}else {
				for(int j=0; j<tmp; ++j) {
					System.out.printf("  ");
				}	
				tmp = tmp - 1;
			}
			System.out.println("*");
		}		
	}

}
