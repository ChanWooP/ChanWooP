package com.anno6;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")  // ±âº» singleton
public class Music {
	public void play() {
		System.out.println("À½¾Ç..."+ toString());
	}
}
