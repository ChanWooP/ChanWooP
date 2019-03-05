package com.test025;

public class Sample {
	
	public int change2(String a) {
		int num = 0;
		int sum = 0;
		int count = a.length();
		
		while(true) {
			char b = a.charAt(--count);
			int temp = 1;
			
			if(b == '1') {
				if(num == 0) {
					sum = sum + 1;
				}else {
					for(int c=1; c<=num; ++c) {
						temp = temp * 2;
					}
					sum = sum + temp;
				}
				++num;
			}else {
				++num;
			}
			
			if(count == 0) {
				break;
			}
			
		}
		return sum;
	}
	
	public int change16(String a) {
		int num = 0;
		int sum = 0;
		int count = a.length();
		String array = "0123456789ABCDEF";
		
		while(true) {
			char b = a.charAt(--count);
			int temp = 1;
			int temp2 = 1;
			
			if(num == 0) {
				temp = temp * array.indexOf(b);
				sum = sum + temp;
			}else {
				for(int c=1; c<=num; ++c) {
					temp2 = temp2 * 16;
				}
				temp = array.indexOf(b) * temp2;
				sum = sum + temp;
			}
			++num;
			
			if(count == 0) {
				break;
			}
			
		}
		return sum;
	}

}
