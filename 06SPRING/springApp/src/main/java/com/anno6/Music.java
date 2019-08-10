package com.anno6;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")  // �⺻ singleton
public class Music {
	public void play() {
		System.out.println("����..."+ toString());
	}
}
