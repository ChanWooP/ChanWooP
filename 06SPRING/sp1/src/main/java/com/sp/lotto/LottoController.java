package com.sp.lotto;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("lotto.lottoController")
public class LottoController {
	// @Autowired
	@Resource(name="lotto.lottoService")
	private LottoService lottoService;
	
	@RequestMapping(value="/lotto/request", method=RequestMethod.GET)
	public String form() {
		return "lotto/write";
	}
	
	@RequestMapping(value="/lotto/request", method=RequestMethod.POST)
	public String submit(int count, Model model) {
		Integer[][] lotto = lottoService.result(count);
		
		model.addAttribute("lotto", lotto);
		
		return "lotto/result";
	}
	
}
