package com.sp.test3;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("test3.testController")
public class TestController {
	@RequestMapping("/test3/main")
	public String main(HttpServletResponse resp) {
		Cookie ck = new Cookie("subject", "spring");
		resp.addCookie(ck);
		
		return "test3/main";
	}
	
	@RequestMapping("/test3/header")
	public String submit(
			@RequestHeader("Accept-Language") String lang, 
			@RequestHeader("User-Agent") String agent,
			HttpServletRequest req,
			Model model) {
		String s = "";
		
		// Referer는 @RequestHeader로 확인 불가
		String referer = req.getHeader("Referer");
		if(referer==null) referer="";
		
		s="헤더정보<br>";
		s+="<hr>클라이언트언어 : "+lang;
		s+="<br>클라이언트브라우저및OS :"+agent;
		s+="<br>클라이언트이전주소 : "+referer;
		
		model.addAttribute("result", s);
		return "test3/view";
	}
	
	@RequestMapping(value="/test3/cookie")
	public String cookie(
			@CookieValue(value="subject", required=false) String subject
			,Model model) {
		String s = "";
		
		model.addAttribute("result", s);
		return "test3/view";
	}

}
