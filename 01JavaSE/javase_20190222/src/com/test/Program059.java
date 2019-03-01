/*
 * 프로그램명 : 석차 알고리즘
 * 작성자 : 박찬우
 * 작성일 : 20190222
 * 프로그램기능 : 석차 알고리즘 설명2
 */

package com.test;

import java.util.*; // 그냥 입력하면 안되서 썼습니다.

public class Program059 {

	public static void main(String[] args) {
		//출력예
		/*
		     과목1     과목2     과목3
		 1)  100(1등) 100(1등)   90(2등)
		 1)  90(2등) 100(1등)   90(2등)
		 1)  100(1등) 100(1등)   90(2등)
		 1)  100(1등) 100(1등)   90(2등)
		 */
		
		//입력
		int[] java = {20,30,60,60};
		int[] c = {40,40,50,10};
		int[] html = {20,100,100,30};
		
		int len = java.length;
		
		int[] jrank = new int[len];
		int[] crank = new int[len];
		int[] hrank = new int[len];
		
		Arrays.fill(jrank, 1);
		Arrays.fill(crank, 1);
		Arrays.fill(hrank, 1);
		
		for(int a=0; a<len; ++a) {
			for(int b=0; b<len; ++b) {
				if(java[a]<java[b]) {
					++jrank[a];
				}
				if(c[a] < c[b]) {
					++crank[a];
				}
				if(html[a] < html[b]) {
					++hrank[a];
				}
			}
		}
		
		System.out.printf("%4s %9s %9s %12s%n","","java","c","html");
		for (int a = 0; a < (len); ++a) {
			System.out.printf("%2d) %5d(%2d등) %5d(%2d등) %5d(%2d등)%n",(a+1), java[a],jrank[a], c[a],crank[a], html[a],hrank[a]);
		}

		
	}

}
