package com.pcw;

public class Program018 {

	public static void main(String[] args) {
		//����) ���� ū ���� ���� ���� �� ����
		//���� a, b, c�� ������ ��(���� ����) ����
		
		//�Է� ����
		//���� ���� �� �ʱⰪ �Ҵ�
		int a=200, b=800, c=4100;
		int max=0, min=0;
		
		//ó������
		//���� a, b, c�� ���� �� ����
		//->���� ū ���� max ������ ����
		//->�������� ���� min ������ ����
		max = (a > b) ? a : b;
		max = (max > c) ? max : c;
		
		min = (a < b) ? a : b;
		min = (min < c) ? min : c;
		
		
		
		//��°���
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(max);
		System.out.println(min);
		
	}

}
