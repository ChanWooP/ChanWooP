package com.test;

public class Program119 {

	public static void main(String[] args) {
        //석차 계산
        //1. 모든 성적의 석차를 1로 설정
        //2. 특정 성적을 가지고, 나머지 성적들과 비교
        //3. 상대방 성적이 높으면, 나의 석차를 +1 연산
        
        //출력 예)
        /*
         1)   100 ( 1등)
         2)    80 ( 6등)
         3)    90 ( 3등)
         4)    50 (10등)
         5)    60 ( 9등)
         6)    85 ( 5등)
         7)    80 ( 6등)
         8)    90 ( 3등)
         9)   100 ( 1등)
        10)    75 ( 8등)
        */
        
        //입력 과정 ----------
        //-> 2차원 배열 사용
        //-> 과목 점수 + 석차 를 2차원 배열로 처리
        //-> {{과목1, 석차1}, {과목2, 점수2}, ...}
        int[][] num = {{100,1},{80,1},{90,1},
						{50,1},{60,1},{85,1},
						{80,1},{90,1},{100,1},{75,1}};
        int len = num.length;

        for(int a=0; a<len; ++a){
            for(int b=0; b<len; ++b){
               if(num[a][0] < num[b][0]){
                   ++num[a][1];
               }
            }
        }

        for(int a=0; a<len; ++a){
            System.out.printf("%2d)%3d (%2d등)%n",a+1, num[a][0],num[a][1]);
        }
	}

}
