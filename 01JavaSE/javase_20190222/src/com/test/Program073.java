package com.test;

public class Program073 {

	public static void main(String[] args) {
		//석차구하기
		
		//과목1,2,3 배열 선언 및 초기화
		int[][] arr1 = {{100,1},{90,1},{89,1},
						{70,1},{60,1},{50,1},
						{40,1},{31,1},{23,1},};
		int[][] arr2 = {{100,1},{90,1},{89,1},
						{70,1},{60,1},{50,1},
						{40,1},{31,1},{23,1},};
		int[][] arr3 = {{100,1},{90,1},{89,1},
						{70,1},{60,1},{50,1},
						{40,1},{31,1},{23,1},};
		//배열 크기저장
		int len = arr1.length;
		
		//석차구하기
		for(int a=0; a<len; ++a) {//비교할 대상 구하기
			for(int b=0; b<len; ++b) {//비교할 대상을 전체 성적과 비교
				if(arr1[a][0] < arr1[b][0]) {
					++arr1[a][1];//비교할 대상이 다른 성적보다 작으면 등수증가
				}
				if(arr2[a][0] < arr2[b][0]) {
					++arr2[a][1];
				}
				if(arr3[a][0] < arr3[b][0]) {
					++arr3[a][1];
				}
			}
		}
		
		System.out.printf("%7s%7s%7s%n","과목1","과목2","과목3");
		for(int a=0; a<len; ++a) {
			System.out.printf("%2d) %3d %d등", (a+1), arr1[a][0], arr1[a][1]); 
			System.out.printf(" %3d %d등", arr2[a][0], arr2[a][1]); 
			System.out.printf(" %3d %d등", arr3[a][0], arr3[a][1]); 
			System.out.println();
		}
	}

}
