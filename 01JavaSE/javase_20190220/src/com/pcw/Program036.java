package com.pcw;

public class Program036 {

	public static void main(String[] args) {
		  //����) ��������� ������ �⵵�� ������, Ư�� �⵵ �޽��� ���
		  //1980����, 1990����, 2000����, 2010����
		  //��¿�)
		  //�������(�⵵): 1992
		  //�޽��� : 1990����
		
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
		
		System.out.printf("����⵵ : %d%n", a);
		System.out.printf("%s����", b);
	}

}
