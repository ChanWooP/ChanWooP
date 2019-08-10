package com.sp.score2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sp.common.MyUtil;

@Controller("score.scoreController2")
public class ScoreController {
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private MyUtil myUtil;
	
	@RequestMapping(value="/score2/main")
	public String main() throws Exception {
		return "score2/main";
	}
	
	@RequestMapping(value="/score2/list")
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(value="pageNo", defaultValue="1") int current_page
			) throws Exception {
		int rows=5;
		int dataCount=scoreService.dataCount();
		int total_page=myUtil.pageCount(rows, dataCount);
		
		if(current_page>total_page)
			current_page=total_page;
		
		int start=(current_page-1)*rows+1;
		int end=current_page*rows;
		
		Map<String, Object> map=new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		List<Score> list=scoreService.listScore(map);
		
		String paging=myUtil.pagingMethod(current_page, total_page, "listPage");
		
		Map<String, Object> model=new HashMap<>();
		model.put("list", list);
		model.put("pageNo", current_page);
		model.put("dataCount", dataCount);
		model.put("paging", paging);
		
		return model;
	}
	
	@RequestMapping(value="/score2/insert", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insert(Score dto) throws Exception {
		Map<String, Object> model=new HashMap<>();
		try {
			model.put("state", "true");
			scoreService.insertScore(dto);
		} catch (Exception e) {
			model.put("state", "false");
		}
		return model;
	}
	
	@RequestMapping(value="/score2/update", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(Score dto) throws Exception {
		Map<String, Object> model=new HashMap<>();
		try {
			model.put("state", "true");
			scoreService.updateScore(dto);
		} catch (Exception e) {
			model.put("state", "false");
		}
		return model;
	}
	
	@RequestMapping(value="/score2/delete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestParam String hak) throws Exception {
		Map<String, Object> model=new HashMap<>();
		try {
			model.put("state", "true");
			scoreService.deleteScore(hak);
		} catch (Exception e) {
			model.put("state", "false");
		}
		return model;
	}
	
}
