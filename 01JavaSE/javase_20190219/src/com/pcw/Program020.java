/*
 * ���α׷��� : ��� �׼�
 * �ۼ��� : ������
 * �ۼ��� : 20190219
 * ���α׷���� : ���� ��� ����
 */
package com.pcw;

public class Program020 {

	public static void main(String[] args) {
		//���ڿ� ��� �׼� �޼ҵ�
		//print(), println(), printf()
		
		int a = 10, b = 20;
		
		System.out.print(a+"\n");//10
		System.out.print(b+"\n");//20
		
		System.out.print(a+b+"\n");//30
		System.out.print(a + ", " + b +"\n");//10, 20
		
		System.out.println(a);
		System.out.println(b);
		
		System.out.printf("%d, %d%n", a,b);
		System.out.printf("%d, %d", a,b);
	}

}
