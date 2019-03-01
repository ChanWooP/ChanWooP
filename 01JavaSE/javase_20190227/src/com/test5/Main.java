package com.test5;

public class Main {

	public static void main(String[] args) {
		CoffeeMachine c1 = new CoffeeMachine();
		
		c1.moonOnOff();
		System.out.println(c1.pushCap());
		System.out.println(c1.pushCap());
		
		c1.moonOnOff();
		System.out.println(c1.mdrop(40));
		
		System.out.println(c1.mdrop(40));
		
		
	}

}
