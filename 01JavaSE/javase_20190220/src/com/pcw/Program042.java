/*
 * ���α׷��� : Math Ŭ����
 * �ۼ��� : ������
 * �ۼ��� : 20190220
 * ���α׷���� : Math Ŭ���� ����
 */
package com.pcw;

public class Program042 {

	public static void main(String[] args) throws InterruptedException {
		//���� �߻�
		//Math Ŭ������ random() �޼ҵ带 �̿��ϸ� ���� �߻� ����
		//���� �⺻ ���� 0 �̻� 1�̸��� �Ǽ�  0.0���� 0.99999~ ������ �߻�
		double a;
		a=Math.random();
		
		//�⺻ ���� ����
		System.out.printf("%f%n", a);//0.0 ~ 0.999~
		
		//Ư�� ���� ����(�ֻ��� 1~6)
		System.out.println(a*6);//0.0 ~ 5.999~
		System.out.println((int)(a*6));//0 ~ 5 - ����ȯ(�Ǽ� -> ����)
		System.out.println((int)(a*6)+1);//1 ~ 6
		
		while(true) {
			int b1 = (int)(Math.random() * 6)+1; // ���� ���� �����ϱ�
			int b2 = (int)(Math.random() * 6)+1;
			System.out.printf("%n(%d, %d)", b1,b2);
			Thread.sleep(1000);
		}
		
	}

}
