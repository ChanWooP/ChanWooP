package com.test;

public class Test {
	public static String decToBin(int n) {
		StringBuilder result = new StringBuilder();
	    while (n != 0) {
            int tmp = n % 2;
            n = n / 2;
            result.insert(0, tmp);
        }
 		return result.toString();
	}
}
