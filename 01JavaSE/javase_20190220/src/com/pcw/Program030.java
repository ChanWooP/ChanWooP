/*
 * ���α׷��� : �����-if��
 * �ۼ��� : ������
 * �ۼ��� : 20190220
 */

package com.pcw;

public class Program030 {

	public static void main(String[] args) {
		//���ǽ��� ����� true�� ��츸 �߰�ȣ({}) �ȿ� �ִ�
		//���ǽ� - ��, �� �����ڸ� �̿��� ����
		/*
			if(Boolean_expression) {
			   // Statements will execute if the Boolean expression is true
			}
		 */
		//���� ��� Ư������(������ 60�� �̻��� ���) ���� �� '�հ�'
		int a = 10;
		
		//�޽��� ����� String �ڷ��� ����
		//���ڿ��� �ֵ���ǥ(")�� ������ �������� ���� ����
		String b = "���հ�";
		
		if(a >= 60) {
			b = "�հ�";
		}
		
		System.out.printf("���� : %d%n", a);
		System.out.printf("��� : %s%n", b);
		//----------------------------------------
		int x = 10;
		String x2 = "";
		
		if( x< 20) {
			x2 = "This is if statment";
		}
		System.out.printf("%s%n",x2);
		
		
	}

}
