package com.test034;

public class Main {

	public static void main(String[] args) {
		String a = "String/Tests";
		
		System.out.println(a.length()); // 결과 : 12
		System.out.println(a.indexOf("s")); // 결과 : 9
		System.out.println(a.lastIndexOf("s")); // 결과 : 11
		
		for(String b : a.split("/")) { // 결과 : String Tests
			System.out.print(b+" ");
		} 
		
	}

}
