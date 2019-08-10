package com.sp.user5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("user5.userController")
public class UserController {
/*	
	// Map을 리턴한 경우 JSP 파일명은 uri가 "/user5/view" 인 경우 "user5/view" 이다.
	@RequestMapping("/user5/view")
	public Map<String, Object> method1() throws Exception {
		Map<String, Object> model = new HashMap<>();
		
		model.put("result", "Map 리턴 타입을 이용하여 포워딩 JSP에 값 전달");
		
		return model;
	}
*/

/*	
	// Model을 리턴한 경우 JSP 파일명은 uri가 "/user5/view" 인 경우 "user5/view" 이다.
	@RequestMapping("/user5/view")
	public Model method1() throws Exception {
		Model model = new ExtendedModelMap();
		
		model.addAttribute("result", "Model 리턴 타입을 이용하여 포워딩 JSP에 값 전달");
		
		return model;
	}
*/	
	
	// ModelMap을 리턴한 경우 JSP 파일명은 uri가 "/user5/view" 인 경우 "user5/view" 이다.
	@RequestMapping("/user5/view")
	public ModelMap method1() throws Exception {
		ModelMap model = new ExtendedModelMap();
		
		model.addAttribute("result", "ModelMap 리턴 타입을 이용하여 포워딩 JSP에 값 전달");
		
		return model;
	}
}
