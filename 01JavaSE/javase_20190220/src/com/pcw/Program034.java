package com.pcw;

public class Program034 {

	public static void main(String[] args) {
		//����) ��������� ������ �⵵�� ������, Ư�� 
		//1980 1990 2000
		//��� ��)
		//�������(����) : 1985
		//�޽��� : 80����
		
		int a = 2019;
		String b = null;
		
		if(a >= 2010) {
			b = "2010";
		}else if(a >= 2000) {
			b = "2000";
		}else if(a >= 1990){
			b = "1990";
		}else {
			b = "1980";
		}
		
		System.out.printf("%s����", b);
		
	}

}
