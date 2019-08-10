package com.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.MyServlet;

@WebServlet("/user/*")
public class UserServlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		
		if(uri.indexOf("addUser.do")!=-1) {
			addUser(req, resp);
		}else if(uri.indexOf("login_ok.do")!=-1) {
			loginSubmit(req, resp);
		}else if(uri.indexOf("logout.do")!=-1) {
			logout(req, resp);
		}
	}
	
	private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		UserDTO dto = new UserDTO();
		
		dto.setUserId(req.getParameter("adduserId"));
		dto.setUserPw(req.getParameter("adduserPw"));
		dto.setUserName(req.getParameter("addName"));
		
		int result = dao.insertUser(dto);
		String msg = "";
		if(result == 0) {
			msg = "회원가입이 실패하였습니다";
			req.setAttribute("msg", msg);
			forward(req, resp, "/WEB-INF/views/login/login.jsp");
			return;
		}
		msg = "회원가입이 성공하였습니다";
		req.setAttribute("msg", msg);
		forward(req, resp, "/WEB-INF/views/login/login.jsp");
	}
	
	private void loginSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		UserDAO dao = new UserDAO();
		String cp=req.getContextPath();
		
		String userId = req.getParameter("userId");
		String userPw = req.getParameter("userPw");
		
		UserDTO dto = dao.readMember(userId);
		if(dto!=null) {
			if(userPw.equals(dto.getUserPw())) {
				session.setMaxInactiveInterval(20*60);
				
				SessionInfo info = new SessionInfo();
				info.setUserId(dto.getUserId());
				info.setUserName(dto.getUserName());
				
				session.setAttribute("user", info);
				
				resp.sendRedirect(cp+"/main/main.do");
				return;
			}
		}
		
		String msg = "아이디 또는 패스워드가 일치하지 않습니다.";
		req.setAttribute("msg", msg);
		
		forward(req, resp, "/WEB-INF/views/login/login.jsp");
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String cp = req.getContextPath();
		
		session.removeAttribute("user");
		
		session.invalidate();
		
		resp.sendRedirect(cp);
	}
}
