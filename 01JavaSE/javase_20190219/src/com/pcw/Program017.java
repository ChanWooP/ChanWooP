package com.pcw;

public class Program017 {

	public static void main(String[] args) {
		//����) ��� ������ ����
		//�Ž����� ��� ������ �ڵ�� �ۼ�
		//���� ���, �ӽú����� 1670���� �ִٰ� ����
		//500, 100, 50, 10��¥�� �����ۿ� ����
		
		//�Է°���
		//���� ���� �� �ʱⰪ �Ҵ�
		int a = 1670;
		int m500 = 0, m100 = 0, m50 = 0, m10 = 0;
		
		//ó������
		//-> ���� ���� ���� �� ������ ����
		m500 = a/500;
		
		m100 = (a%500)/100;
		
		m50 = (a%500%100)/50;
		
		m10 = (a%500%100%50)/10;
		
		System.out.println(a);
		System.out.println(m500);
		System.out.println(m100);
		System.out.println(m50);
		System.out.println(m10);
		
		System.out.printf("�Ž����� : %d%n500��:%d, 100��:%d, 50��:%d, 10��:%d%n",a,m500,m100,m50,m10);
	}

}
