/*
 * 프로그램명 : 성적 및 개인정보 입출력
 * 작성자 : 박찬우
 * 작성일 : 20190226
 */

package com.test;

public class Program114 {

	public static void main(String[] args) {
		java.util.Scanner sc = new java.util.Scanner(System.in); 
		
		
		String[][] members = new String[10][]; //개인정보 저장 배열
		int count = members.length; // 개인정보 저장 배열의 크기
		int idx = 0; // 성적정보 인덱스
		int midx = 0; // 회원정보 인덱스
		
		// 점수 저장 배열
		// 개인정보 배열 크기만큼만 입력 가능하도록
		int[][] students = new int[count][];
		
		while(true) {
			System.out.println("---성적 처리---");
			System.out.println("1.개인정보출력  2.개인정보입력  3.성적정보출력 4.성적정보입력");
			System.out.print("선택>");
			int choice = sc.nextInt();
			sc.nextLine();
			int total1 = 0, total2 = 0, total3 = 0; // 성적 총합 
			double avg1 = 0.0, avg2 = 0.0, avg3 = 0.0; // 성적 평균
			String name = "";
			
			
			if(choice == 0) { // 종료
				break;
			}else if(choice == 1) { // 성적정보출력
				System.out.printf("%3s / %3s / %10s / %s%n", "학번","이름","전화번호","이메일");
				for(int a=0; a<midx; ++a) {
					String[] member = members[a];
					System.out.printf("%3s / %3s / %13s / %20s%n",member[0],member[1],member[2],member[3]);
				}	 
			}else if(choice == 2) {
				if(midx < 10) {
					String[] member = new String[4];
					System.out.print("학번>");
					member[0] = sc.nextLine();
					System.out.print("이름>");
					member[1] = sc.nextLine();
					System.out.print("전화번호>");
					member[2] = sc.nextLine();
					System.out.print("이메일");
					member[3] = sc.nextLine();
					
					System.out.print("내용을 저장하시겠습니까?(0/1)>");
					int a = sc.nextInt();
					if(a == 1) {
						members[midx++] = member;
						System.out.println("입력완료!");
					}else {
						System.out.println("입력취소!");
					}
				}else {
					System.out.println("인원수가 초가되었습니다!");
				}
					
			}else if(choice == 3) {
				int len = idx; // 성적배열 크기
				System.out.println("--------------------------------------------");
				System.out.printf("%2s / %3s / %3s / %3s / %3s / %3s / %2s%n",
						"학번", "이름","과목1","과목2","과목3","총점","등수");
				
				//점수 및 이름 출력
				for(int a=0; a<len; ++a) {
					int[] student = students[a]; // 1차원배열을 
					
					//등수 계산
					student[5] = 1;
					for(int b=0; b<len; ++b) {
						int[] student2 = students[b];
						if(student[4] < student2[4]) {
							++student[5];
						}
					}
					
					//이름 검색
					for(int c=0; c<len; ++c) {
						String member[] = members[c];
						String num = String.valueOf(student[0]);
						if(num.equals(member[0])) {
							name = member[1];
						}
					}
					
					//출력
					System.out.printf("%2d / %3s / %3d / %3d / %3d / %3d / %2d%n",
							 student[0], name, student[1], student[2], student[3], student[4], student[5]);
				}
				
				//과목별반평균 계산 및 출력
				for(int a=0; a<len; ++a) {
					int[] student = students[a];
					total1 = total1 + student[1];
					total2 = total2 + student[2];
					total3 = total3 + student[3];
				}
				
				avg1 = total1 / (double)len;
				avg2 = total2 / (double)len;
				avg3 = total3 / (double)len;
				
				
				System.out.println("------------");
				System.out.println("과목별반평균");
				System.out.printf("과목1 :%.2f%n",avg1);
				System.out.printf("과목2 :%.2f%n",avg2);
				System.out.printf("과목3 :%.2f%n%n",avg3);
			}else if(choice == 4) {
				if(idx < midx) {
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
						students[idx++] = student;
						System.out.println("입력완료!");
					}else {
						System.out.println("입력취소!");
					}
				}else {
					System.out.println("인원이 없습니다!");
				}
				
			}
		}
		sc.close();
		System.out.print("프로그램 종료");
	}

}
