package com.pcw;

public class Program021 {

	public static void main(String[] args) {
		  //����) ���� ó�� ��� ���
		  //������ ���� ���� �غ�
		  //����, ��� ��� ���� �߰�
		  //���� ���� ��� ���� �߰� - ��� ��½� �Ҽ����� �� �ڸ��� ���
		  //��¿�)
		  /*
		  ����1: 100
		  ����2: 90
		  ����3: 78
		  ����: 268
		  ���: 89.3
		  */
		int kor = 100, eng = 90, math = 78;
		int total = 0;
		double avg = 0.0;
		
		total = kor + eng + math;
		avg = total / 3.0;
		
		System.out.printf("--------------------------------%n");
		System.out.printf("           �������             %n");
		System.out.printf("--------------------------------%n");
		System.out.printf("���� : %d%n", kor);
		System.out.printf("���� : %d%n", eng);
		System.out.printf("���� : %d%n", math);
		System.out.printf("���� : %d%n", total);
		System.out.printf("��� : %.1f%n", avg);
	}

}
