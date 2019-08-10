package com.sp.test2;

import java.util.List;
//import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("test2.testController")
public class TestController {
	@RequestMapping("/test2/main")
	public String main() {
		return "test2/main";
	}
	
	//파라미터를 맵으로 받기(@RequestParam 필요) 파라미터이름이 동일하면 하나만 받음
	@RequestMapping("/test2/request")
	public String submit(@RequestParam Map<String, String> paramMap, Model model) {
		String s = "";
/*	
		Iterator<String> it = paramMap.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String val = paramMap.get(key);
			System.out.println(key+":"+val);
		}
*/		
		s = "이름:"+paramMap.get("name")+", 나이:"+paramMap.get("age")+", 성별:"+paramMap.get("gender");
		
		model.addAttribute("result", s);
		return "test2/view";
	}
	
	//동일한 이름의 파라미터를 List로 받기(@RequestParam 필요). 배열로 받을 수 있다.(Integer[] nums)
	@RequestMapping("/test2/request2")
	public String submit2(@RequestParam List<Integer> nums, Model model) {
		String s = "";
		
		for(Integer n : nums)
			s+=n+" ";
		
		model.addAttribute("result", s);
		return "test2/view";
	}
}
