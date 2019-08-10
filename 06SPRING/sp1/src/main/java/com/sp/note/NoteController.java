package com.sp.note;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("note.noteController")
public class NoteController {
	@Autowired
	private NoteService noteService;
	
	@RequestMapping(value="/note/request", method=RequestMethod.GET)
	public String form(Model model) {
		List<Note> friendList = noteService.listFriend();
		
		model.addAttribute("friendList", friendList);
		return "note/write";
	}
	
	@RequestMapping(value="/note/request", method=RequestMethod.POST)
	public String submit(Note dto, Model model) {
		model.addAttribute("dto", dto);
		return "note/result";
	}
}
