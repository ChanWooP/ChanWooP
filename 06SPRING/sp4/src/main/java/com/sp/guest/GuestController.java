package com.sp.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sp.member.LoginService;
import com.sp.member.SessionInfo;

@Controller("guest.guestController")
public class GuestController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/guest")
	public String guest() {
		SessionInfo info=loginService.loginMemberInfo();
		
		if(info==null)
			return "redirect:/login";
		
		return "guest/guest";
	}
}
