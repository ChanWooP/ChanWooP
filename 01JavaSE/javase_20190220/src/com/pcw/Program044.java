package com.pcw;

public class Program044 {

	public static void main(String[] args) {
		 //난수 발생
		  //Math 클래스의 random() 메소드를 이용하면 난수 발생 가능
		  //난수 기본 범위 0 이상,  1 미만의 실수. 0.0부터 0.999999 까지만 발생한다.
		  
		  //문제) 랜덤한 길이(범위 1~50)의 랜덤한 문자(범위 a~z) 출력.
		  /*
		  출력예)
		  출력 횟수:5
		  adsfdsf
		  erqwe
		  cvzcvcbcxbcvb
		  erqggfbsfdhgadgferwaefzxcdvdtweafzdvadf
		  asfasdfdwq3rewfesafas
		  */
		
		int b = 0;
		int c = 0;
		char d = 'a';
		
		for(int a=1;a<=5;++a) {
			b = (int)(Math.random() * 50)+1;
			while(true) {
				++c;
				d = (char) ((Math.random() * 26) + 97);
				System.out.printf("%c",d);
				
				if(c>=b) {
					c=0;
					break;
				}
				
			}
			System.out.println();
		}
	}

}
