package com.pcw;

public class Program032 {

	public static void main(String[] args) {
		  
		  //���ǽ��� ����� true�� ��츸 �߰�ȣ({}) �ȿ� �ִ� ���๮ ���� ����
		  //���ǽ� - ��, �� �����ڸ� �̿��� ����
		  /*
		  if(Boolean_expression) {
		     // Statements will execute if the Boolean expression is true
		  } 
		  */
		  
		  
		  //����) Ư�� ���ڰ� 3�� �������, �ƴ����� �Ǻ��ؼ� �޽��� ���
		  //��¿�)
		  //����: 9
		  //���: 3�� ����Դϴ� 
		
		int a = 99;
		String b = "3�� ����� �ƴմϴ�";
				
		if(a%3 == 0) {
			b = "3�� ����Դϴ�";
		}
		System.out.printf("%d%n", a);
		System.out.printf("%s%n", b);
	}

}
