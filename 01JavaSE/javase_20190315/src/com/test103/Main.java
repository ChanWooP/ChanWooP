package com.test103;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.print("자료입력>");
			int m = sc.nextInt();
			sc.nextLine();
			
			//예외가 발생되지 않는 경우 실행할 수 있는 문장
			System.out.printf("정상출력 : %d%n",m);
			
		}catch(Exception e) {
			
			//실제 예외 발생시 실행할 문장
			//->에러 메시지 출력
			//e.printStackTrace(); // 예외내용
			System.out.println(e.getMessage());
			
		}
		sc.close();
		System.out.println("끝");
	}

}
