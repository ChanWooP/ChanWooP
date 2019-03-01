package com.pcw;

public class Program041 {

	public static void main(String[] args) {
		// 1 ~ 100 수중 3의 배수만 출력
		// 출력예)
		//배수 : 3
		//3, 6, 9, 12...99
		
		int a = 3;
		int c = 0;
		for(int b=1; b<=100; ++b) {
			if(b%a == 0) {
				System.out.printf("%d", b);
				c = c + b;
				if(b>=(100-a)) {
					System.out.printf("=");
				}else{
					System.out.printf("+");
				}
			}
		}
		System.out.printf("%d", c);
	}

}
