package com.sp.hello;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("hello.helloController")
public class HelloController {
	
	@RequestMapping("/hello") // method를 지정하지 않으면 GET, POST 모두 처리
	public String execute(Model model) throws Exception{
		Calendar cal=Calendar.getInstance();
		
		String s=String.format("%tF %tT", cal, cal);
		
		model.addAttribute("result", s); // request.setAttribute("result", s);
		
		return "hello/main"; // 포워딩할 JSP 파일명
	}
}
