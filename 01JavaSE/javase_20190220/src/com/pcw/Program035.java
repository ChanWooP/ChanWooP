package com.pcw;

public class Program035 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 109;
		String b = null;
				
		switch(a % 3) {
		case 0 :
			b = "3�� ����Դϴ�.";
			break;
		default :
			b = "3�� ����� �ƴմϴ�.";
		}
		
		System.out.printf("%d%n", a);
		System.out.printf("%s%n", b);
	}

}
