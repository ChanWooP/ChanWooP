package com.pcw;

public class Program040 {

	public static void main(String[] args) {
		// 1 ~ 100 ���� 3�� ����� ���
		// ��¿�)
		//��� : 3
		//3, 6, 9, 12...99
		
		int a = 3;
		
		for(int b=1; b<=100; ++b) {
			if(b%3 == 0) {
				System.out.printf("%d ", b);
			}
		}

	}

}
