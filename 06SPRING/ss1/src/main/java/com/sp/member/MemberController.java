package com.sp.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// security

@Controller("member.memberController")
public class MemberController {
	@RequestMapping(value="/login")
	public String login(String login_error, Model model) {
		boolean bLoginError=login_error!=null;
		
		if(bLoginError) {
			model.addAttribute("message", "아이디 또는 패스워드가 일치하지 않습니다.");
		}
		
		return "member/login";
	}
	
	// 접근 오서라이제이션(Authorization:권한)이 없는 경우
	@RequestMapping(value="/noAuthorized")
	public String noAuthorized() {
		
		return "member/noAuthorized";
	}
	
}
