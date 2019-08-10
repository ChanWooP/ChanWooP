package com.sp.movie;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("movie.movieController")
public class MovieController {
	
	@RequestMapping(value="/movie/form", method=RequestMethod.GET)
	public String form(Model model) {
		
		return "movie/main";
	}
	
	@RequestMapping(value="/movie/submit", method=RequestMethod.POST)
	public String submit(@RequestParam List<String> list, Model model) {
		
		model.addAttribute("list",list);
		model.addAttribute("start",2);
		return "movie/main";
	}
}
