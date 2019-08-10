package com.sp.note;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("note.noteService")
public class NoteServiceImpl implements NoteService {

	@Override
	public List<Note> listFriend() {
		List<Note> list = new ArrayList<>();
		Note dto;
		
		dto=new Note(); dto.setUserId("kim"); dto.setUserName("김김김"); 
		list.add(dto);
		dto=new Note(); dto.setUserId("lee"); dto.setUserName("이이이");
		list.add(dto);
		dto=new Note(); dto.setUserId("han"); dto.setUserName("한한한");
		list.add(dto);
		dto=new Note(); dto.setUserId("neo"); dto.setUserName("너너너");
		list.add(dto);
		dto=new Note(); dto.setUserId("heo"); dto.setUserName("허허허");
		list.add(dto);
		dto=new Note(); dto.setUserId("da"); dto.setUserName("다다다");
		list.add(dto);
		dto=new Note(); dto.setUserId("ka"); dto.setUserName("가가가");
		list.add(dto);
		
		return list;
	}
}
