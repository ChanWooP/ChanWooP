package com.pcw;

public class Program043 {

	public static void main(String[] args) throws InterruptedException {
		int count = 0;
		while(true) {
			int b1 = (int)(Math.random() * 6)+1; // ���� ���� �����ϱ�
			int b2 = (int)(Math.random() * 6)+1;
			System.out.printf("%d��° : (%d, %d)%n",++count, b1, b2);
			Thread.sleep(1000);
			
			//Ư�� ���� ���� �� while �� ����������
			if(b1==6 && b2==6) {
				System.out.printf("����");
				break;
			}
		}
	}
}