package com.sp.bbs;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.common.MyUtil;

@Controller("bbs.boardController")
public class BoardController {
	@Autowired
	private MyUtil myUtil;
	@Autowired
	private BoardService service;

	
	@RequestMapping(value="bbs/created", method=RequestMethod.GET)
	public String createdForm(Model model) throws Exception{
		model.addAttribute("mode","created");
		return "bbs/created";
	}
	
	@RequestMapping(value="bbs/created", method=RequestMethod.POST)
	public String createdSubmit(Board dto, HttpServletRequest req) throws Exception{
		try {
			dto.setIpAddr(req.getRemoteAddr());
			service.insertBoard(dto);
		} catch (Exception e) {
		}
		return "redirect:/bbs/list";
	}
	
	@RequestMapping("/bbs/list")
	public String list(
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(defaultValue="all") String condition,
			@RequestParam(defaultValue="") String keyword,
			HttpServletRequest req,
			Model model) throws Exception{
		String cp = req.getContextPath();
		
		int rows=10;
		int total_page=0;
		int dataCount=0;
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			keyword=URLDecoder.decode(keyword, "UTF-8");
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		dataCount = service.dataCount(map);
		if(dataCount>0)
			total_page = myUtil.pageCount(rows, dataCount);
		
		if(current_page>total_page)
			current_page = total_page;
		
		int start = (current_page-1)*rows+1;
		int end = current_page*rows;
		
		map.put("start", start);
		map.put("end", end);
		
		List<Board> list = service.listBoard(map);
		int listNum, n=0;
		for(Board dto : list) {
			listNum = dataCount-(start+n-1);
			dto.setListNum(listNum);
			n++;
		}
		
		String query = "";
		String listUrl;
		String articleUrl;
		if(keyword.length()!=0) {
			query = "condition="+condition+"&keyword="+
					URLEncoder.encode(keyword, "UTF-8");
		}
		
		listUrl = cp+"/bbs/list";
		articleUrl = cp+"/bbs/article?page="+current_page;
		if(query.length()!=0) {
			listUrl+="?"+query;
			articleUrl+="&"+query;
		}
		String paging = myUtil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("list",list);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("page", current_page);
		model.addAttribute("total_page", total_page);
		model.addAttribute("paging", paging);
		model.addAttribute("condition",condition);
		model.addAttribute("keyword",keyword);
		model.addAttribute("articleUrl",articleUrl);
		
		return "bbs/list";
	}
	
	@RequestMapping(value="/bbs/article", method=RequestMethod.GET)
	public String article(
			@RequestParam int num,
			@RequestParam String page,
			@RequestParam(defaultValue="all") String condition,
			@RequestParam(defaultValue="") String keyword,
			Model model
			) throws Exception {
		keyword=URLDecoder.decode(keyword, "UTF-8");
		
		String query = "page="+page;
		if(keyword.length()!=0) {
			query += "&condition="+condition+"&keyword="+
						URLEncoder.encode(keyword, "UTF-8");
		}
		
		service.updateHitCount(num);
		
		Board dto = service.readBoard(num);
		if(dto==null)
			return "redirect:/bbs/list?"+query;
		
		dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		map.put("num", num);
		
		Board preReadDto = service.preReadBoard(map);
		Board nextReadDto = service.nextReadBoard(map);
		
		model.addAttribute("dto",dto);
		model.addAttribute("preReadDto",preReadDto);
		model.addAttribute("nextReadDto",nextReadDto);
		model.addAttribute("query", query);
		model.addAttribute("page",page);
		
		return "bbs/article";
	}
	
	@RequestMapping(value="/bbs/update", method=RequestMethod.GET)
	public String updateForm(
			@RequestParam int num,
			@RequestParam String page,
			Model model
			) throws Exception{
		
		Board dto = service.readBoard(num);
		if(dto==null)
			return "redirect:/bbs/list?page="+page;
		
		model.addAttribute("mode", "update");
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		
		return "bbs/created";
	}	
	
	@RequestMapping(value="/bbs/update", method=RequestMethod.POST)
	public String updateSubmit(
			Board dto,
			@RequestParam String page
			) throws Exception{
		
		try {
			service.updateBoard(dto);
		} catch (Exception e) {
		}
		
		return "redirect:/bbs/list?page="+page;
	}
	
	@RequestMapping(value="/bbs/delete")
	public String delete(
			@RequestParam int num,
			@RequestParam String page,
			@RequestParam(defaultValue="all") String condition,
			@RequestParam(defaultValue="") String keyword
			) throws Exception{
		keyword=URLDecoder.decode(keyword, "UTF-8");
		String query = "page="+page;
		if(keyword.length()!=0) {
			query+="&condition="+condition+"&keyword="+URLEncoder.encode(keyword, "UTF-8");
		}
		
		try {
			service.deleteBoard(num);
		} catch (Exception e) {
		}
		
		return "redirect:/bbs/list?"+query;
	}
}
