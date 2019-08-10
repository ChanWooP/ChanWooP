package com.sp.test4;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("test4.testController")
@RequestMapping(value="/test4/*")
public class TestController {
	
	@RequestMapping(value="request", method=RequestMethod.GET)
	public String form(Model model) {
		List<String> haks = new ArrayList<>();
		haks.add("대졸");
		haks.add("고졸");
		haks.add("기타");
		
		model.addAttribute("haks",haks);
		
		return "test4/write";
	}
	
	// @ModelAttribute가 메소드 위에 있으면 @RequestMapping 보다 먼저 실행
	// @RequestMapping가 적용되지 않는 별도의 메소드에서 모델에 추가될 객체 생성
	// 메소드의 인수는 HttpServletRequest 등의 사용이 가능하다.
	@ModelAttribute("memberTypes")
	public List<String> getMemberType() throws Exception{
		List<String> list = new ArrayList<>();
		list.add("일반회원");
		list.add("기업회원");
		list.add("직원회원");
		return list;
	}
	
	/*
	@RequestMapping(value="request", method=RequestMethod.POST)
	public String submit(Test vo, Model model) {
		model.addAttribute("dto",vo);
		return "test4/view";
	}
	*/
	
	// 위와 동일한 개념
	// @ModelAttribute를 메소드 인자로 사용하는 경우 화면의 form 속성으로 넘오온 model을
	// 메팽된 method의 파라미터로 지정해주는 역할을 한다.
	@RequestMapping(value="request", method=RequestMethod.POST)
	public String submit(@ModelAttribute("dto") Test vo) {
		return "test4/view";
	}
}
