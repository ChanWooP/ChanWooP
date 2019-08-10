package com.sp.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("main.mainController")
public class MainController {
	@RequestMapping(value="/main")
	public String main() {
		return "main/main";
	}
}
