package com.sp.score;

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

@Controller("score.scoreController")
@RequestMapping("/score/*")
public class ScoreController {
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private MyUtil myutil;
	
	@RequestMapping(value="list")
	public String list(
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(defaultValue="hak") String condition,
			@RequestParam(defaultValue="") String keyword,
			HttpServletRequest req,
			Model model) throws Exception{
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			keyword=URLDecoder.decode(keyword, "UTF-8");
		}
		
		int rows=10;
		int dataCount, total_page;
		Map<String, Object> map = new HashMap<>();
		map.put("condition",condition);
		map.put("keyword", keyword);
		
		dataCount = scoreService.dataCount(map);
		total_page = myutil.pageCount(rows, dataCount);
		if(current_page > total_page)
			current_page = total_page;
		
		int start = (current_page - 1) * rows * 1;
		int end = current_page * rows;
		
		map.put("start", start);
		map.put("end", end);
		
		List<Score> list = scoreService.listScore(map);
		
		String cp = req.getContextPath();
		String listUrl = cp+"/score/list";
		if(keyword.length()!=0) {
			listUrl+="?condition="+condition+"&keyword="+
					URLEncoder.encode(keyword, "UTF-8");
		}
		String paging = myutil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("list", list);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("page", current_page);
		model.addAttribute("paging", paging);
		
		return "score/list";
	}
	
	@RequestMapping(value="insert", method=RequestMethod.GET)
	public String insertForm(Model model) throws Exception{
		model.addAttribute("mode","insert");
		return "score/write";
	}
	
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public String insertSubmit(Score dto, Model model){
		
		try {
			scoreService.insertScore(dto);
		} catch (Exception e) {
			model.addAttribute("mode","insert");
			model.addAttribute("msg","학번 중복등의 사유로 추가가 실패했습니다.");
			return "score/write";
		}
		
		return "redirect:/score/list";
	}
	
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String updateForm(
			@RequestParam String hak,
			@RequestParam String page, Model model) {
		Score dto = scoreService.readScore(hak);
		if(dto==null)
			return "redirect:/score/list?page="+page;
		
		model.addAttribute("mode", "update");
		model.addAttribute("dto",dto);
		model.addAttribute("page",page);
		
		return "score/write";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String updateSubmit(Score dto, @RequestParam String page) {
		try {
			scoreService.updateScore(dto);
		} catch (Exception e) {
		}
		return "redirect:/score/list?page="+page;
	}
	
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String delete(@RequestParam String hak,
			@RequestParam String page) {
		try {
			scoreService.deleteScore(hak);
		} catch (Exception e) {
		}
		return "redirect:/score/list?page="+page;
	}
	
	@RequestMapping(value="deleteList", method=RequestMethod.POST)
	public String deleteList(@RequestParam List<String> haks,
			@RequestParam String page) {
		try {
			scoreService.deleteScoreList(haks);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/score/list?page="+page;
	}
}
