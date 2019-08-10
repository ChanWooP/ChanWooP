package com.sp.main;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("mainController")
public class MainController {
	 
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String method(HttpSession session) {
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId=null;
		boolean isAnonymous=true;
		
		if(principal instanceof UserDetails) {
			userId=((UserDetails) principal).getUsername();
			isAnonymous=false;
		}
		
		session.setAttribute("username", userId);
		session.setAttribute("isAnonymous", isAnonymous);
		
		return "main/main";
	}
}
