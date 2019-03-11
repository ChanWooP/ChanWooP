package com.test064;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Member m1 = new Member("as1","2019-01-01");
		Member m2 = new Member("bs2","2019-02-02");
		Member m3 = new Member("cs3","2019-03-03");
		
		Member[] ms = {m1,m2,m3};
		
		System.out.println(Arrays.toString(ms));
		
	
	}

}
