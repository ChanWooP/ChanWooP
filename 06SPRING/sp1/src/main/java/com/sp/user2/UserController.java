package com.sp.user2;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("user2.userController")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user2/request", method=RequestMethod.GET)
	public String form() {
		return "calc2/write";
	}
	
	/*
	@RequestMapping(value="/user2/request", method=RequestMethod.POST)
	public ModelAndView submit(int num1, int num2, String operator) {
		User dto=new User();
		dto.setNum1(num1);
		dto.setNum2(num2);
		dto.setOperator(operator);
		
		String s = userService.result(dto);
		
		// ModelAndView : view와 view에 넘길 데이터를 담고있는 객체
		ModelAndView mav=new ModelAndView("calc2/result");
		mav.addObject("calculator", s);
		return mav;
	}
	*/

	/*
	@RequestMapping(value="/user2/request", method=RequestMethod.POST)
	public ModelAndView submit(HttpServletRequest req) {
		User dto=new User();
		dto.setNum1(Integer.parseInt(req.getParameter("num1")));
		dto.setNum2(Integer.parseInt(req.getParameter("num2")));
		dto.setOperator(req.getParameter("operator"));
		
		String s = userService.result(dto);
		
		// ModelAndView : view와 view에 넘길 데이터를 담고있는 객체
		ModelAndView mav=new ModelAndView("calc2/result");
		mav.addObject("calculator", s);
		return mav;
	}
	 */
	
	@RequestMapping(value="/user2/request", method=RequestMethod.POST)
	public void submit(HttpServletRequest req, HttpServletResponse resp) 
	    throws Exception{
		User dto=new User();
		dto.setNum1(Integer.parseInt(req.getParameter("num1")));
		dto.setNum2(Integer.parseInt(req.getParameter("num2")));
		dto.setOperator(req.getParameter("operator"));
		
		String s = userService.result(dto);
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		
		out.print("<html><body>");
		out.print("결과:"+s);
		out.print("</body></html>");

	}

}
