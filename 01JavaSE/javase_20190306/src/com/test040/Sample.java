package com.test040;

import java.util.Random;

public class Sample {
	private int array[];
	
	public Sample() {
		Random r = new Random();
		this.array = new int[6];
		int len = this.array.length;
		
		for(int a=0; a<len; ++a) {
			boolean b = true;
			int temp = r.nextInt(45) + 1;
			
			for(int i : array) {
				if(temp == i) {
					b = false; 
					--a;
				}
			}
			
			if(b) this.array[a] = temp;
		}
	}
	
	public int[] getArray() {
		return array;
	}
}
