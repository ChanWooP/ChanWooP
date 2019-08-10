package com.sp.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("blog.blogController")
public class BlogController {
	@Autowired
	
	private BlogService blogService;
	
	@RequestMapping(value="/blog")
	public String main(Model model) {
		List<Blog> list = blogService.listBlog();
		
		model.addAttribute("list",list);
		
		return "blog/blog";
	}
	
	/*
	 * @PathVariable 이노테이션
	 * URI 템플릿을 이용하여 REST 방식의 URL 매칭처리를 위한 이노테이션
	 * 블로그, SNS등 유저별로 페이지를 구축할때 편리\
	 * 
	 * [방법]
	 * - @RequestMapping 이노테이션의 값으로 {탬플릿변수}를 사용
	 * - 메소드 인수에서 PathVariable을 이용하여 {템플릿변수}와 동일한 이름을 갖는 파라미터 추가
	 */
	
	@RequestMapping(value="/blog/{blogId}/main")
	public String blogMain(@PathVariable String blogId, Model model) throws Exception {
		
		Blog dto = null;
		
		List<Blog> list = blogService.listBlog();
		for(Blog vo : list) {
			if(vo.getBlogId().equals(blogId)) {
				dto = vo;
				break;
			}
		}
		
		model.addAttribute("dto",dto);
		
		return "blog/main";
	}
}
