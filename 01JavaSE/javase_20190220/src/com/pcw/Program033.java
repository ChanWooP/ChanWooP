package com.pcw;

public class Program033 {

	public static void main(String[] args) {
		//ù ��° ���ǽ��� ����� true�� ��� if��({}) �ȿ� �ִ� ���๮ ���� ����
				//ù ��° ���ǽ��� ����� false�� ��� else if��({})�� ���ǽ� �˻�
				//else if ���� ���ǽ� ����� true����, false������ ���� Ư�� ���๮ ���� ����
				//���ǽ� - ��, �� �����ڸ� �̿��� ����
				/*
				if(Boolean_expression 1) {
				   // Executes when the Boolean expression 1 is true
				} else if(Boolean_expression 2) {
				   // Executes when the Boolean expression 2 is true
				} else if(Boolean_expression 3) {
				   // Executes when the Boolean expression 3 is true
				} else {
				   // Executes when the none of the above condition is true.
				} 
				*/
				//���� ���, 
				//Ư�� ����1(������ 90�� �̻�) ������ 'A' �޽��� ���
				//Ư�� ����2(������ 80�� �̻�) ������ 'B' �޽��� ���
				//Ư�� ����3(������ 70�� �̻�) ������ 'C' �޽��� ���
				//Ư�� ����4(������ 60�� �̻�) ������ 'D' �޽��� ���
				//Ư�� ����5(������ 60�� �̸�) ������ 'F' �޽��� ���
		
		int a =50;
		String b = null;
		
		if(a >= 90) {
			b = "A���";
		}else if(a >= 80) {
			b = "B���";
		}else if(a >= 70) {
			b = "C���";
		}else if(a >= 60) {
			b = "D���";
		}else {
			b = "F���";
		}
		
		switch(a/10) {
		case 10:
		case 9:
			b = "A���";
			break;
		case 8:
			b = "B���";
			break;
		case 7:
			b = "C���";
			break;
		case 6:
			b = "D���";
			break;
		default:
			b = "F���";
		}
		
		System.out.printf("���� : %d%n",a);
		System.out.printf("��� : %s%n",b);
	}

}
