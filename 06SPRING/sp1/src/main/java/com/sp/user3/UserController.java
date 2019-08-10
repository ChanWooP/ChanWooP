package com.sp.user3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("user3.userController")
public class UserController {
	@RequestMapping(value="/user3/request", method=RequestMethod.GET)
	public String form() {
		return "user3/write";
	}
	
	/*
	@RequestMapping(value="/user3/request", method=RequestMethod.POST)
	public String request(String[] hobby, Model model) {
		// 클라이언트가 전송한 파라미터 이름이 동일하면 배열로  받을 수 있다.
		// 파라미터 이름이 동일하게 2개이상이 넘어온 경우 String hobby 처럼
		//    넘겨 받으면 hobby 는 "음악,운동" 처럼 넘어 온다.
		
		String s="";
		
		if(hobby!=null) {
			for(String h:hobby)
				s+=h+" | ";
		}
		
		model.addAttribute("s", s);
		
		return "user3/result";
	}
	*/
	
	@RequestMapping(value="/user3/request", method=RequestMethod.POST)
	public String request(User dto, Model model) {
		// dto 에서는 동일한 파라미터는 List로 넘겨 받을 수 있다.
		
		model.addAttribute("s", "선택한 취미는");
		model.addAttribute("dto", dto);
		
		return "user3/result";
	}
	
	
}
