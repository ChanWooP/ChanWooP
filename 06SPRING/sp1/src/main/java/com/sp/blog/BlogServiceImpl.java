package com.sp.blog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("blog.blogService")
public class BlogServiceImpl implements BlogService{

	@Override
	public List<Blog> listBlog() {
		List<Blog> list = new ArrayList<>();
		Blog dto = null;
		
		dto = new Blog();
		dto.setBlogId("java");
		dto.setUserName("자바");
		dto.setBlogName("자바스터디");
		list.add(dto);
		
		dto = new Blog();
		dto.setBlogId("spring");
		dto.setUserName("스프링");
		dto.setBlogName("스프링스터디");
		list.add(dto);
		
		dto = new Blog();
		dto.setBlogId("web");
		dto.setUserName("웹");
		dto.setBlogName("웹스터디");
		list.add(dto);
		
		return list;
	}
	
}
