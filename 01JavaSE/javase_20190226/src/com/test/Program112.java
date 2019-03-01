/*
 * 프로그램명 : 과목별 입력 및 출력
 * 작성자 : 박찬우
 * 작성일 : 20190226
 */

package com.test;

public class Program112 {

	public static void main(String[] args) {
		//성적 처리
		//성적 처리 구성 - 학번, 과목1, 과목2, 과목3, 총점, 등수
		//2차원 배열 저장소 운영
		//1차원 배열 - 개개인의 성적 정보 저장하는 저장소
		//2차원 배열 - 성적 정보 여러개(10)를 저장하는 저장소
		//{{1, 100, 100, 100, 0, 1}, {1, 90, 90, 90, 0, 1, ...}}
		//주의 : 배열은 동일 자료형만 허용
		//주의 : 휘발성 자료
		//출력 예)
		/*
		 ---성적 처리---
		 1. 성적정보출력 2. 성적정보입력
		 선택> 1
		 과목1>1
		 과목2>1
		 과목3>1
		 내용을 저장하시겠습니까?(0/1)>1
		 입력완료!
		 */
		java.util.Scanner sc = new java.util.Scanner(System.in);
		int[][] students = new int[10][];
		int count = 0;
		
		while(true) {
			System.out.println("---성적 처리---");
			System.out.println("1. 성적정보출력 2. 성적정보입력");
			System.out.print("선택>");
			int choice = sc.nextInt();
			sc.nextLine();
			int total1 = 0, total2 = 0, total3 = 0;
			double avg1 = 0.0, avg2 = 0.0, avg3 = 0.0;
			
			
			if(choice == 0) {
				break;
			}else if(choice == 1) {
				int len = count;
				System.out.println("--------------------------------------------");
				System.out.printf("%2s / %3s / %3s / %3s / %3s / %2s%n",
						"학번","과목1","과목2","과목3","총점","등수");
				for(int a=0; a<len; ++a) {
					int[] student = students[a];
					student[5] = 1;
					
					for(int b=0; b<len; ++b) {
						int[] student2 = students[b];
						if(student[4] < student2[4]) {
							++student[5];
						}
					}
					System.out.printf("%2d / %3d / %3d / %3d / %3d / %2d%n",
							student[0], student[1], student[2], student[3], student[4], student[5]);
				}
				
				for(int a=0; a<len; ++a) {
					int[] student = students[a];
					total1 = total1 + student[1];
					total2 = total2 + student[2];
					total3 = total3 + student[3];
				}
				
				avg1 = total1 / (double)len;
				avg2 = total2 / (double)len;
				avg3 = total3 / (double)len;
				
				//과목별반평균 계산 및 출력
				System.out.println("------------");
				System.out.println("과목별반평균");
				System.out.printf("과목1 :%.2f%n",avg1);
				System.out.printf("과목2 :%.2f%n",avg2);
				System.out.printf("과목3 :%.2f%n%n",avg3);
				
				
				 
			}else if(choice == 2) {
				if(count <10) {
					int[] student = new int[6];
					System.out.print("학번>");
					student[0] = sc.nextInt();
					sc.nextLine();
					System.out.print("과목1>");
					student[1] = sc.nextInt();
					sc.nextLine();
					System.out.print("과목2>");
					student[2] = sc.nextInt();
					sc.nextLine();
					System.out.print("과목3>");
					student[3] = sc.nextInt();
					sc.nextLine();
					student[4] = student[1] + student[2] + student[3]; 
		
					System.out.print("내용을 저장하시겠습니까?(0/1)>");
					int a = sc.nextInt();
					sc.nextLine();
					if(a == 1) {
						students[count++] = student;
						System.out.println("입력완료!");
					}else {
						System.out.println("입력취소!");
					}
				}else {
					System.out.println("인원수가 초가되었습니다!");
				}
					
			}
		}
		sc.close();
		System.out.print("프로그램 종료");
	}

}
