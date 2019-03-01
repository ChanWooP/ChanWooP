package com.pcw;

public class Program039 {

	public static void main(String[] args) {
		//문제) 구구단 중에서 5단 출력
		//출력예)
		//5 * 1 = 5
		
		int a = 5;
		
		
		int b=1;
		while(b < 10)
		{
			System.out.printf("%d * %d = %d%n", a, b, (a*b));
			++b;
		}
	}

}
