/*
 * 프로그램명 : 성적관리
 * 작성자 : 박찬우
 * 작성일 : 20190218
 * 프로그램기능 : 시험 성적을 입력받아 계산하고 출력함
 */

package javase_20190218;

public class Program {

	public static void main(String[] args) {
		//입력단계
		//시험과목들
		int kor, eng, math;
		//시험 성적를 저장할 변수들
		int total;
		float avg;
		//시험 성적(과목1, 과목2, 과목3) 입력
		kor = 60;
		eng = 70;
		math = 80;
		total = 0;
		avg = 0;
		
		//처리단계
		//시험 성적(총점, 평균) 계산
		total = kor + eng + math;
		avg = total / 3;
		
		//출력단계
		//시험 성적(총점, 평균) 출력
		System.out.printf("총점:%d, 평균%f", total, avg);
	}

}
