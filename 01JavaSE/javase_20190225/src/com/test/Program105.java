/*
 * 프로그램명 : 숫자 스무고개
 * 작성자 : 박찬우
 * 작성일 : 20190225
 */

package com.test;

public class Program105 {

	public static void main(String[] args){
		//문제)
		//숫자 맞추기(스무고개)
		//컴퓨터가 제시하는 임의의 숫자(1 ~ 50)를 특정 횟수(10번)내에서 맞춘다.
		//출력예)
		/*
		컴퓨터가 제시한 숫자:??
		입력1:50
		보다 작다
		입력2:40
		보다 크다
		입력3:45
		맞췄습니다!
		프로그램 종료
		*/
		java.util.Scanner sc = new java.util.Scanner(System.in);
		int a = (int)(Math.random()*50)+1;
		int b = 0;
		System.out.printf("컴퓨터가 제시한 숫자:??(%d)%n", a);
		for(int i=0; i<10; ++i) {
			System.out.printf("입력%d:",i+1);
			b = sc.nextInt();
			sc.nextLine();
			if(b < a) {
				System.out.printf("보다 크다%n");
			}else if(b > a) {
				System.out.printf("보다 작다%n");
			}else {
				System.out.printf("맞췄습니다!%n");
				break;
			}
			
			if(i == 10-1) {
				System.out.printf("기회가 다 떨어졌습니다!%n");
			}
		}
		
		System.out.printf("%s","프로그램종료");
		sc.close();
	}

}
