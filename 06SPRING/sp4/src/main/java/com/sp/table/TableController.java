package com.sp.table;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("table.tableController")
public class TableController {
	@Autowired
	private TableService service;
	
	@RequestMapping(value="/table/main")
	public String main() throws Exception{
		return "table/main";
	}
	
	@RequestMapping(value="/table/request", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createTable(
			@RequestParam String tableName
			) throws Exception{
		
		String state = "true";
		
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("tableName", "cboard_"+tableName);
			
			service.createBoardTable(map);
		} catch (Exception e) {
			state="false";
		}
		
		Map<String, Object> model = new HashMap<>();
		model.put("state", state);
		return model;
	}
}
