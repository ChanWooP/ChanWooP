package com.pcw;

public class Program036 {

	public static void main(String[] args) {
		  //문제) 생년월일의 가지고 년도를 가지고, 특정 년도 메시지 출력
		  //1980년대생, 1990년대생, 2000년대생, 2010년대생
		  //출력예)
		  //생년월일(년도): 1992
		  //메시지 : 1990년대생
		
		int a = 1996;
		String b = null;
		
		switch(a/10)
		{
		case 201:
			b = "2010";
			break;
		case 200:
			b = "2000";
			break;
		case 199:
			b = "1990";
			break;
		default:
			b = "1980";
		}
		
		System.out.printf("출생년도 : %d%n", a);
		System.out.printf("%s년대생", b);
	}

}
