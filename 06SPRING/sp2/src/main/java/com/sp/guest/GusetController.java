package com.sp.guest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sp.common.MyUtil;

@Controller("guest.guestController")
public class GusetController {
	
	@Autowired
	private GuestService service;
	@Autowired
	private MyUtil myUtil;
	// 일반
	@RequestMapping("/guest/guest")
	public String main() {
		return "guest/guest";
	}
	
	// AJAX - Text
	@RequestMapping("guest/list")
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
		
		return "guest/list";
	}
	
	/*
	 * @ResponseBody : 자바 객체를 HTTP 응답 몸체로 전송
	 */
	@RequestMapping(value="/guest/insert", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertGuest(Guest dto) throws Exception{
		Map<String, Object> model = new HashMap<>();
		
		try {
			service.insertGuest(dto);
			model.put("state", "true");
		} catch (Exception e) {
			model.put("state", "false");
		}
		
		return model;
	}
	
	@RequestMapping(value="/guest/delete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(
			@RequestParam int num
			) {
		Map<String, Object> model = new HashMap<>();
		try {
			service.deleteGuest(num);
			model.put("state", "true");
		} catch (Exception e) {
			model.put("state", "false");
		}
		return model;
	}
}
