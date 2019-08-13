package com.sp.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("person.personController")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@RequestMapping("/person/list")
	public String list() {
		return "person/list";
	}
	
	@RequestMapping("/person/article")
	public String article() {
		return "person/article";
	}
	
	@RequestMapping(value="/person/article", method=RequestMethod.POST)
	public String insertPerson(Person dto) throws Exception {
		
		try {
			service.insertPerson(dto);
		} catch (Exception e) {
		}
		
		return "redirect:/person/list";
	}
}
