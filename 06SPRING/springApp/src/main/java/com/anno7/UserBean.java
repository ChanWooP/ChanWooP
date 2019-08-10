package com.anno7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBean {
	@Autowired
	private Movie movie;
	@Autowired
	private Music music;
	
	public void execute() {
		movie.play();
		music.play();
	}
}
