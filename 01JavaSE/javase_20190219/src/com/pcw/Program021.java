package com.pcw;

public class Program021 {

	public static void main(String[] args) {
		  //문제) 성적 처리 결과 출력
		  //임의의 과목 점수 준비
		  //총점, 평균 계산 과정 추가
		  //서식 지정 출력 과정 추가 - 평균 출력시 소수이하 한 자리만 출력
		  //출력예)
		  /*
		  과목1: 100
		  과목2: 90
		  과목3: 78
		  총점: 268
		  평균: 89.3
		  */
		int kor = 100, eng = 90, math = 78;
		int total = 0;
		double avg = 0.0;
		
		total = kor + eng + math;
		avg = total / 3.0;
		
		System.out.printf("--------------------------------%n");
		System.out.printf("           성적출력             %n");
		System.out.printf("--------------------------------%n");
		System.out.printf("국어 : %d%n", kor);
		System.out.printf("영어 : %d%n", eng);
		System.out.printf("수학 : %d%n", math);
		System.out.printf("총점 : %d%n", total);
		System.out.printf("평균 : %.1f%n", avg);
	}

}
