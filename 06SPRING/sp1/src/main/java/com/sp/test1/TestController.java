package com.sp.test1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("test1.testController")
public class TestController {
	@RequestMapping(value="/test1/main")
	public String form() {
		return "test1/main";
	}
	
	@RequestMapping(value="/test1/request")
	public String method1(int age, String gender, Model model) throws Exception{
		String s = "성인";
		if(age<19) s = "미성년자";
		
		String result = "성별 : "+gender+", "+s;
		
		model.addAttribute("result",result);
		
		return "test1/view";
	}
	
	
	@RequestMapping(value="/test1/request2")
	public String method2(@RequestParam int age,@RequestParam String gender, Model model) throws Exception{
		String s = "성인";
		if(age<19) s = "미성년자";
		
		String result = "성별 : "+gender+", "+s;
		
		model.addAttribute("result",result);
		
		return "test1/view";
	}
	
	@RequestMapping(value="/test1/request3")
	public String method3(@RequestParam int age,@RequestParam(required=false) String gender, Model model) throws Exception{
		String s = "성인";
		if(age<19) s = "미성년자";
		
		String result = "성별 : "+gender+", "+s;
		
		model.addAttribute("result",result);
		
		return "test1/view";
	}
	
	@RequestMapping(value="/test1/request4")
	public String method4(@RequestParam(defaultValue="0") int age,@RequestParam(value="g", defaultValue="f") String gender, Model model) throws Exception{
		String s = "성인";
		if(age<19) s = "미성년자";
		
		String result = "성별 : "+gender+", "+s;
		
		model.addAttribute("result",result);
		
		return "test1/view";
	}
}

