package com.pcw;

public class Program044 {

	public static void main(String[] args) {
		 //���� �߻�
		  //Math Ŭ������ random() �޼ҵ带 �̿��ϸ� ���� �߻� ����
		  //���� �⺻ ���� 0 �̻�,  1 �̸��� �Ǽ�. 0.0���� 0.999999 ������ �߻��Ѵ�.
		  
		  //����) ������ ����(���� 1~50)�� ������ ����(���� a~z) ���.
		  /*
		  ��¿�)
		  ��� Ƚ��:5
		  adsfdsf
		  erqwe
		  cvzcvcbcxbcvb
		  erqggfbsfdhgadgferwaefzxcdvdtweafzdvadf
		  asfasdfdwq3rewfesafas
		  */
		
		int b = 0;
		int c = 0;
		char d = 'a';
		
		for(int a=1;a<=5;++a) {
			b = (int)(Math.random() * 50)+1;
			while(true) {
				++c;
				d = (char) ((Math.random() * 26) + 97);
				System.out.printf("%c",d);
				
				if(c>=b) {
					c=0;
					break;
				}
				
			}
			System.out.println();
		}
	}

}
