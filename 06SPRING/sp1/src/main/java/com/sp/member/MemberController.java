package com.sp.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@SessionAttributes("member")
@Controller("member.memberController")
public class MemberController {
	
	@ModelAttribute("member")
	public Member domain() {
		return new Member();
	}
	
	@RequestMapping(value="/member/form", method=RequestMethod.GET)
	public String memberForm() throws Exception{
		return "member/step1";
	}
	
	@RequestMapping(value="/member/step1", method=RequestMethod.POST)
	public String submitStep1(@ModelAttribute("member") Member member) throws Exception{
		// 이름, 이메일
		return "member/step2";
	}

	@RequestMapping(value="/member/step2", method=RequestMethod.POST)
	public String submitStep2(@ModelAttribute("member") Member member,
			SessionStatus sessionStatus,
			Model model) throws Exception{
		
		
		String msg;
		msg = "이름 : "+member.getName()+"<br>";
		msg += "이메일 : "+member.getEmail()+"<br>";
		msg += "아이디 : "+member.getId()+"<br>";
		msg += "패스워드 : "+member.getPwd()+"<br>";
		msg += "전화번호 : "+member.getTel()+"<br>";
		
		// 세션에 저장된 내용 지우기
		sessionStatus.setComplete();
		
		model.addAttribute("message", msg);
		
		return "member/complete";
	}
	
}
