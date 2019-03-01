/*
 * 프로그램명 : 과목별반평균
 * 작성자 : 박찬우
 * 작성일 : 20190226
 */

package com.test;

public class Program111 {

	public static void main(String[] args) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		
		//2차원 배열 저장소 준비
		int[][] scores = new int[10][];
		
		//2차원 배열 인덱스 운영용 변수
		int cnt = 0;
		
		//출력 액션 테스트용 임시 초기값 지정
		int[] s1 = {1, 100, 100, 100, 0, 0};
		int[] s2 = {2, 90, 100, 80, 0, 0};
		int[] s3 = {3, 100, 90, 90, 0, 0};
		scores[0] = s1;
		scores[1] = s2;
		scores[2] = s3;
		cnt = 3;

		while (true) {
			System.out.println();
			System.out.println("--- 성적 처리 ---");
			System.out.println("1.성적정보출력  2.성적정보입력");
			System.out.print("선택>");
			int m = sc.nextInt();
			sc.nextLine();
			if (m == 0) break;
			if (m == 1) {
				System.out.println("1.성적정보출력");
				
				//전체 성적에 대한 총점 계산 과정 추가
				
				//전체 성적에 대한 등수 계산 과정 추가
				//주의) 인원이 추가될 때마다 재계산 필요
				
				//전체 성적 정보 출력 과정 추가
				int len = cnt;
				int total1 = 0;
				int total2 = 0;
				int total3 = 0;
				double avg1 = 0.0;
				double avg2 = 0.0;
				double avg3 = 0.0;
				
				System.out.println("--------------------------------");
				System.out.println("학번/과목1/과목2/과목3/총점/평균");
				
				for(int a = 0; a < len; ++a) {
					int[] score = scores[a];
					score[4] = score[1]+ score[2]+ score[3];
					score[5] = 1;
				}
				
				for (int a = 0; a < len; ++a) {
					int[] score = scores[a];
					
					for(int b = 0; b < len; ++b) {
						int[] score2 = scores[b];
						if(score[4] < score2[4]) {
							++score[5];
						}
					}
					
					
					System.out.printf("%d / %d / %d / %d / %d / %d%n"
							, score[0], score[1], score[2], score[3], score[4], score[5]);
				}
				
				
				for(int a=0; a<len; ++a) {
					int[] score = scores[a];
					total1 = total1 + score[1];
					total2 = total2 + score[2];
					total3 = total3 + score[3];	
				}
				avg1 = total1 / (double)len;
				avg2 = total2 / (double)len;
				avg3 = total3 / (double)len;
				
				//과목별반평균 계산 및 출력
				System.out.println("------------");
				System.out.println("과목별반평균");
				System.out.printf("과목1 :%.2f%n",avg1);
				System.out.printf("과목2 :%.2f%n",avg2);
				System.out.printf("과목3 :%.2f%n",avg3);
				
			} else if (m == 2){
				System.out.println("2.성적정보입력");
			
			} 
		}
		
		sc.close();
		System.out.println("프로그램 종료");
	}

}
