package com.sp.guest;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sp.common.MyUtil;

@Controller("guest.guestController3")
public class GusetController3 {
	
	@Autowired
	private GuestService service;
	@Autowired
	private MyUtil myUtil;
	// 일반
	@RequestMapping("/guest3/guest")
	public String main() {
		return "guest3/guest";
	}
	
	// AJAX - XML
	@RequestMapping("guest3/list")
	public String list(
			@RequestParam(value="pageNo", defaultValue="1") int current_page
			,Model model
			) {
		
		int rows = 5;
		int dataCount = service.dataCount();
		int total_page = myUtil.pageCount(rows, dataCount);
		
		if(current_page>total_page)
			current_page=total_page;
		
		int start = (current_page-1)*rows+1;
		int end = current_page*rows;
		
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		List<Guest> list = service.listGuest(map);
		for(Guest dto : list) {
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
		}
		
		// listPage : 자바스크립트 함수명
		String paging = myUtil.pagingMethod(current_page, total_page, "listPage");
		
		model.addAttribute("list",list);
		model.addAttribute("pageNo",current_page);
		model.addAttribute("dataCount",dataCount);
		model.addAttribute("total_page",total_page);
		model.addAttribute("paging",paging);
		
		return "guest3/list";
	}
	
	// AJAX - XML
	@RequestMapping(value="/guest3/insert", method=RequestMethod.POST)
	@ResponseBody
	public void insertGuest(Guest dto, HttpServletResponse resp) throws Exception{

		String state = "true";
		try {
			service.insertGuest(dto);
		} catch (Exception e) {
			state="false";
		}
		
		String xml = "<state>"+state+"</state>";
		resp.setContentType("text/xml; charset=utg-8");
		PrintWriter out = resp.getWriter();
		out.println(xml);
	}
	
	// AJAX - XML
	@RequestMapping(value="/guest3/delete", method=RequestMethod.POST)
	public void delete(
			@RequestParam int num,
			HttpServletResponse resp
			) throws Exception{

		String state = "true";
		try {
			service.deleteGuest(num);
		} catch (Exception e) {
			state="false";
		}
		
		String xml = "<state>"+state+"</state>";
		resp.setContentType("text/xml; charset=utg-8");
		PrintWriter out = resp.getWriter();
		out.println(xml);
	}
}
