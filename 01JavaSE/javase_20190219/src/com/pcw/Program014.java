/*
 * ���α׷��� : ����
 * �ۼ��� : ������
 * �ۼ��� : 20190219
 * ���α׷���� : ���� ����2
 */

package com.pcw;

public class Program014 {

	public static void main(String[] args) {

		//���� �ڷ����� �������� ���� ���� ����
		//���� ����� �ʱⰪ �Ҵ��� ���� ���� ����
		//int �ڷ����� �⺻ �ʱⰪ 0 
		//double �ڷ����� �⺻ �ʱⰪ 0.0 
		int kor = 60, eng = 70, math = 80;
		int total = 0;
		double avg = 0.0;
		
		total = kor + eng + math;
		avg = total / 3;
		
		System.out.printf("����:%d, ���%f", total, avg);
	}

}
