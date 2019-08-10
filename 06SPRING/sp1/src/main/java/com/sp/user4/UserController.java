package com.sp.user4;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("user4.userController")
public class UserController {
	
	@RequestMapping(value="/user4/map")
	public String method1(Map<String, Object> model) throws Exception {
		model.put("result", "Map 인터페이스 파라미터 타입을 이용하여 포워딩 JSP에 값 전달");
		return "user4/view";
	}
	
	@RequestMapping(value="/user4/model")
	public String method2(Model model) throws Exception {
		model.addAttribute("result", "Model 인터페이스 파라미터 타입을 이용하여 포워딩 JSP에 값 전달");
		return "user4/view";
	}

	@RequestMapping(value="/user4/modelMap")
	public String method3(ModelMap model) throws Exception {
		model.addAttribute("result", "ModelMap 클래스 파라미터 타입을 이용하여 포워딩 JSP에 값 전달");
		return "user4/view";
	}
	
	@RequestMapping(value="/user4/modelView")
	public ModelAndView method4() throws Exception {
		ModelAndView mav=new ModelAndView(); // new ModelAndView("user4/view");
		mav.setViewName("user4/view"); // jsp 명
		mav.addObject("result", "ModelAndView 리턴 타입을 이용하여 포워딩 JSP에 값 전달");
		return mav;
	}
}

