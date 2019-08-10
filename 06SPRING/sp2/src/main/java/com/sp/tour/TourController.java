package com.sp.tour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("tour.tourController")
public class TourController {
	
	@Autowired
	private TourService service;
	
	@RequestMapping("/tour/main")
	public String main() {
		return "tour/main";
	}
	
	@RequestMapping("/tour/tour")
	public String tour(Model model) {
		List<Tsido> sidoList = service.listTour();
		
		model.addAttribute("sidoList",sidoList);
		
		return "tour/tour";
	}
	
	@RequestMapping("/tour/cityList")
	@ResponseBody
	public Map<String, List<Tcity>> cityList(int num){
		Map<String, List<Tcity>> model = new HashMap<>();
		try {
			List<Tcity> list = service.listCity(num);
			model.put("list", list);
		} catch (Exception e) {
		}
		return model;
	}
	
	@RequestMapping("/tour/manage")
	public String manage(Model model) {
		List<Tsido> sidoList = service.listTour();
		
		model.addAttribute("sidoList",sidoList);
		
		return "tour/manage";
	}
	
	@RequestMapping("/tour/insertTsido")
	@ResponseBody
	public Map<String, Object> insertTsido(String sido){
		Map<String, Object> model = new HashMap<>();
		
		try {
			service.insertTsido(sido);
			model.put("state", "true");
		} catch (Exception e) {
			model.put("state", "false");
		}
		
		return model;
	}
	
	@RequestMapping("/tour/deleteTsido")
	@ResponseBody
	public Map<String, Object> deleteTsido(int num){
		Map<String, Object> model = new HashMap<>();
		
		try {
			service.deleteTsido(num);
			model.put("state", "true");
		} catch (Exception e) {
			model.put("state", "false");
		}
		
		return model;
	}
	
	@RequestMapping("/tour/insertTcity")
	@ResponseBody
	public Map<String, Object> insertTcity(int num, String city){
		Map<String, Object> model = new HashMap<>();
		
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("snum", num);
			map.put("city", city);
		
			service.insertTcity(map);
			model.put("state", "true");
		} catch (Exception e) {
			model.put("state", "false");
		}
		
		return model;
	}
	
	@RequestMapping("/tour/deleteTcity")
	@ResponseBody
	public Map<String, Object> deleteTcity(int num){
		Map<String, Object> model = new HashMap<>();
		
		try {
			service.deleteTcity(num);
			model.put("state", "true");
		} catch (Exception e) {
			model.put("state", "false");
		}
		
		return model;
	}
}
