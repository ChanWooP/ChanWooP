package com.sp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("user.userController")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/request", method=RequestMethod.GET)
	public String form() {
		return "calc/write";
	}
	
	@RequestMapping(value="/user/request", method=RequestMethod.POST)
	public String submit(User dto, Model model) {

		String s = userService.result(dto);
		
		// 포워딩하는 JSP에 넘겨주는 값(request.setAttribute()와 같음)
		model.addAttribute("calculator", s);
		
		return "calc/result";
	}
	
}
