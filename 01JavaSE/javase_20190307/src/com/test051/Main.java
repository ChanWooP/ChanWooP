package com.test051;

import com.test050.*;

public class Main {

	public static void main(String[] args) {
		Sample s = new Sample();
		
		//s.method2(); - default로 선언해서 패키지 내부에서만 접근 가능함
		s.method3();
	}

}
