/*
 * ���α׷��� : Ȧ¦Ȯ��
 * �ۼ��� : ������
 * �ۼ��� : 20190220
 * ���α׷���� : Ȧ¦Ȯ���ϱ�
 */

package com.pcw;

public class Program031 {

	public static void main(String[] args) {
		  //���ǽ��� ����� true�� ��츸 �߰�ȣ({}) �ȿ� �ִ� ���๮ ���� ����
		  //���ǽ� - ��, �� �����ڸ� �̿��� ����
		  /*
		  if(Boolean_expression) {
		     // Statements will execute if the Boolean expression is true
		  } 
		  */
		  
		  
		  //����) Ư�� ���ڰ� Ȧ������, ¦������ �Ǻ��ؼ� �޽��� ���
		  //��¿�)
		  //����: 9
		  //���: Ȧ��
		
		int number = 1025;
		String name = "Ȧ��";
		
		if(number%2 == 0)
		{
			name = "¦��";
		}
		
		System.out.printf("%d%n", number);
		System.out.printf("%s%n", name);
	}

}
