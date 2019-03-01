/*
 * 프로그램명 : 연산자
 * 작성자 : 박찬우
 * 작성일 : 20190219
 * 프로그램기능 : 연산자 설명
 */

package com.pcw;

public class Program016 {

	public static void main(String[] args) {
		//연산자
		//메모리에 저장된 자료에 대한 계산 과젱에 필요한 기호
		//1. Arithmetic Operators
		//2. Relational Operators
		//3. Bitwise Operators
		//4. Logical Operators
		//5. Assignment Operators
		//6. Misc Operators
		
		//Assignment Operators
		//연산 결과나 초기값 등을 특정 변수에 저장할 때
		//변수 = 값 또는 수식
		//변수의 자료형의 범위를 벗어나는 값은 지정 불가
		
		//변수 선언
		int a;
		int b;
		
		//10이라는 값을 변수 a에 할당
		a = 10;
		
		//산술 연산의 결과값을 변수 a에 할당(덮어쓰기)
		a = a + 10;
		
		//---------------논리---------------------------------------		
		int d,e,f,g,i;
		double h;
		
		a = 10;
		
		d = a + 5; // 15
		e = a - 5; // 5
		f = a * 5; // 50
		g = a / 3; // 3
		h = a / 3.0; // 3.3
		i = a % 5; // 0
		
		System.out.println(d+","+e+","+f+","+g+","+h+","+i);
		//------------------------------------------------------
		
		//------------비교------------------------------------------		
		boolean j,k,l,m,n,o;
		
		a = 10;
		b = 20;
		
		j = a == b;
		k = a!= b;
		l = a > b;
		m = a < b;
		n = a >= b;
		o = a <= b;
		
		System.out.println(j+","+k+","+l+","+m+","+n+","+o);
		//------------------------------------------------------
		
		//------------논리------------------------------------------		
		boolean p,q,r;
		boolean t,t1;
		
		t = true;
		t1 = false;
		
		p = t&&t1;
		q = t||t1;
		r = !t;
		
		System.out.println(p+","+q+","+r);
		//------------------------------------------------------
		
		//------------기타------------------------------------------
		int a1;
		a = 20;
		b = 10;
		
		a1 = (a > b) ? a : b;
		System.out.println(a1);
		//------------------------------------------------------
	}

}
