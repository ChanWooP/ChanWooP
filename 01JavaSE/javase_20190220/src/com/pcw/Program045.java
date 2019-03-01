package com.pcw;

public class Program045 {

	public static void main(String[] args) {
		 //난수 발생
		  //Math 클래스의 random() 메소드를 이용하면 난수 발생 가능
		  //난수 기본 범위 0 이상,  1 미만의 실수. 0.0부터 0.999999 까지만 발생한다.
		  
		  //문제) 랜덤한 길이(범위 1~10)의 랜덤한 문자(범위 a~z) 출력.
		  /*
		  출력예)
		  Adsfdsf erqwe cvzcvcbc eqggfbsfdh. Asfas dafd aerqwefsd vcxzv. Daefadf daf faef cvzxcv efefaf.
		  */
		
		int b = 0;
		int c = 0;
		char d = 'a', e = 'a';
		
		for(int a=1;a<=5;++a) {
			b = (int)(Math.random() * 10)+1;
			while(true) {
				++c;
				
				if(c==1) {
					e= (char) ((Math.random() * 26) + 65);
					System.out.printf("%c", e);
				}
				else
				{
					d = (char) ((Math.random() * 26) + 97);
					System.out.printf("%c", d);
				}
				if(c>=b) {
					System.out.print(". ");
					c=0;
					break;
				}
				
			}
		}
		
		
	}

}
