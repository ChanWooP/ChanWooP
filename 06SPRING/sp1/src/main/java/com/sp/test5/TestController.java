package com.sp.test5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("test5.testController")
@RequestMapping(value="/test5/*")
public class TestController {
	
	@RequestMapping(value="request", method=RequestMethod.GET)
	public String form(Model model) {
		List<String> haks = new ArrayList<>();
		haks.add("대졸");
		haks.add("고졸");
		haks.add("기타");
		
		List<String> list = new ArrayList<>();
		list.add("일반회원");
		list.add("기업회원");
		list.add("직원회원");
		
		model.addAttribute("haks",haks);
		model.addAttribute("memberTypes",list);
		return "test5/write";
	}
	
	
	/*
	 * RedirectAttributes에 데이터를 저장하면 리다이렉트된 후 즉시 사라지고
	 * 사용자가 F5를 눌러도 서버로 다시 submit 되지 않게 한다.
	 */
	@RequestMapping(value="request", method=RequestMethod.POST)
	public String submit(Test dto,
			final RedirectAttributes rattr) {
		
		rattr.addFlashAttribute("dto",dto);
		rattr.addFlashAttribute("msg","end...");
		
		return "redirect:/test5/show";
	}
	
	@RequestMapping(value="show")
	public String result(@ModelAttribute("dto") Test dto) {
		System.out.println("아이디:"+dto.getId());
		// 메소드 파라미터에서 @ModelAttribute("dto")를 붙이지 않으면 포워딩된 jsp에서는 
		// 값이 넘어가지만 위의 dto.getId()는 null이 된다
		
		return "test5/view";
	}
}
