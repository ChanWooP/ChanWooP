package com.pcw;

public class Program043 {

	public static void main(String[] args) throws InterruptedException {
		int count = 0;
		while(true) {
			int b1 = (int)(Math.random() * 6)+1; // 난수 값을 저장하기
			int b2 = (int)(Math.random() * 6)+1;
			System.out.printf("%d번째 : (%d, %d)%n",++count, b1, b2);
			Thread.sleep(1000);
			
			//특정 조건 만족 시 while 블럭 빠져나가기
			if(b1==6 && b2==6) {
				System.out.printf("종료");
				break;
			}
		}
	}
}