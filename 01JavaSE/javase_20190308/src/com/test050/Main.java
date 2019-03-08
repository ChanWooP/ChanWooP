package com.test050;

public class Main {

	public static void main(String[] args) {
		Sub sub = new Sub();
		
		sub.method1(); 
		
		sub.method2();
		
		//sub.privateMethod(); - private 맴버 상속 불가능
		
		sub.protectedMethod(); // - protected 맴버는 상속가능(다른 패키지에서는 불가능)
	}

}
