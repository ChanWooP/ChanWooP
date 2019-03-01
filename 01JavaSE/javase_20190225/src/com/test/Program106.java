/*
 * 프로그램명 : 가위바위보 게임
 * 작성자 : 박찬우
 * 작성일 : 20190225
 */

package com.test;

public class Program106 {

	public static void main(String[] args) {
		//문제)
		//가위(1), 바위(2), 보(3)
		//컴퓨터는 임의의 숫자(1 ~ 3)를 제시하고
		//사용자도 임의의 숫자(1 ~ 3)를 제시한다.
		//두 수를 비교해서 게임의 승자를 출력한다.
		//출력예)
		/*
	  	컴퓨터가 제시한 숫자(1 ~ 3):??
	  	사용자가 제시한 숫자(1 ~ 3)>1
	  	컴퓨터:가위(1), 사용자:가위(1) = 무승부
	  	컴퓨터가 제시한 숫자(1 ~ 3):??
	  	사용자가 제시한 숫자(1 ~ 3)>2
	  	컴퓨터:가위(1), 사용자:바위(2) = 사용자승!
	  	컴퓨터가 제시한 숫자(1 ~ 3):??
	  	사용자가 제시한 숫자(1 ~ 3)>3
	  	컴퓨터:가위(1), 사용자:보(3) = 컴퓨터승!
	  	컴퓨터가 제시한 숫자(1 ~ 3):??
	  	사용자가 제시한 숫자(1 ~ 3)>0
	  	프로그램 종료
		 */
	  
		//컴퓨터가 임의의 난수 발생 -> Math 클래스의 random() 메소드
		//사용자 외부 입력 -> Scanner 클래스의 nextInt() 메소드
		//임의의 난수와 사용자 입력한 수를 비교
		//무승부, 사용자승!, 컴퓨터승!
		int a =0,b =0;
		java.util.Scanner sc = new java.util.Scanner(System.in);
		
		while(true) {
			a = (int)(Math.random()*3)+1;
			System.out.printf("컴퓨터가 제시한 숫자 (1 ~ 3):??(%d)%n",a);
			
			System.out.printf("사용자가 제시한 숫자 (1 ~ 3)>");
			b = sc.nextInt();
			sc.nextLine();
			
			
			if(b == 0) {
				break;
			}
			
			if(a == b) {
				System.out.printf("무승부%n");
			}else if((a == 1 && b==2) || (a == 2 && b == 3) || (a == 3 && b == 1)) {
				System.out.printf("사용자승%n");
			}else {
				System.out.printf("컴퓨터승%n");
			}
		}
		System.out.printf("%s","프로그램종료");
		sc.close();
	}
}
