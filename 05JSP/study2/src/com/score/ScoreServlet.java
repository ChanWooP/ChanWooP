package com.score;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.util.MyUtil;


@WebServlet("/score/*")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
		RequestDispatcher rd=req.getRequestDispatcher(path);
		rd.forward(req, resp);
	}
	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String uri=req.getRequestURI();  // cp 부터 끝까지 주소
		
		if(uri.indexOf("list.do")!=-1) {
			// 글리스트
			list(req, resp);
		} else if(uri.indexOf("insert.do")!=-1) {
			// 글쓰기폼
			insertForm(req, resp);
		} else if(uri.indexOf("insert_ok.do")!=-1) {
			// 글등록
			insertSubmit(req, resp);
		} else if(uri.indexOf("update.do")!=-1) {
			// 글수정폼
			updateForm(req, resp);
		} else if(uri.indexOf("update_ok.do")!=-1) {
			// 글수정완료
			updateSubmit(req, resp);
		} else if(uri.indexOf("delete.do")!=-1) {
			// 글삭제
			delete(req, resp);
		}
	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ScoreDAO dao = new ScoreDAO();
		MyUtil util = new MyUtil();
		
		String page = req.getParameter("page");
		int current_page = 1;
		if(page!=null)
			current_page = Integer.parseInt(page);
		
		int dataCount = dao.dataCount();
		int rows = 10;
		int total_page = util.pageCount(rows, dataCount);
		
		if(current_page>total_page)
			current_page=total_page;
		
		int start = (current_page-1)*rows+1;
		int end = current_page*rows;
		
		List<ScoreDTO> list = dao.listScore(start, end);
		String cp = req.getContextPath();
		String listUrl = cp+"/score/list.do";
		String paging=util.paging(current_page, total_page, listUrl);
		List<Integer> list2 = dao.averageScore();
		
		req.setAttribute("list2", list2);
		req.setAttribute("list", list);
		req.setAttribute("dataCount", dataCount);
		req.setAttribute("page", current_page);
		req.setAttribute("paging", paging);
		req.setAttribute("total_page", total_page);
		
		String path="/WEB-INF/views/score/list.jsp";
		forward(req, resp, path);
	}	

	protected void insertForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글쓰기폼
		req.setAttribute("mode", "insert");
		String path="/WEB-INF/views/score/write.jsp";
		forward(req, resp, path);
	}	
	
	protected void insertSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글저장
		ScoreDAO dao = new ScoreDAO();
		ScoreDTO dto = new ScoreDTO();
		
		dto.setHak(req.getParameter("hak"));
		dto.setName(req.getParameter("name"));
		dto.setBirth(req.getParameter("birth"));
		dto.setKor(Integer.parseInt(req.getParameter("kor")));
		dto.setEng(Integer.parseInt(req.getParameter("eng")));
		dto.setMat(Integer.parseInt(req.getParameter("mat")));
		
		try {
			dao.insertScore(dto);
		} catch (Exception e) {
			req.setAttribute("mode", "insert");
			req.setAttribute("msg", "학번 중복등으로 인하여 데이터 추가가 실패했습니다.");
			String path="/WEB-INF/views/score/write.jsp";
			forward(req, resp, path);
			return;
		}
		// 리스트로 리다이렉트
		String cp=req.getContextPath();
		resp.sendRedirect(cp+"/score/list.do");
	}	

	protected void updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String hak = req.getParameter("hak");
		String page = req.getParameter("page");
		
		ScoreDAO dao = new ScoreDAO();
		ScoreDTO dto = dao.readScore(hak);
		
		if(dto==null) {
			String cp = req.getContextPath();
			resp.sendRedirect(cp+"/score/list.do?page="+page);
			return;
		}
		
		req.setAttribute("dto", dto);
		req.setAttribute("page", page);
		
		req.setAttribute("mode", "update");
		forward(req, resp, "/WEB-INF/views/score/write.jsp");
	}	
	
	protected void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ScoreDAO dao = new ScoreDAO();
		ScoreDTO dto = new ScoreDTO();
		
		dto.setHak(req.getParameter("hak"));
		dto.setName(req.getParameter("name"));
		dto.setBirth(req.getParameter("birth"));
		dto.setKor(Integer.parseInt(req.getParameter("kor")));
		dto.setEng(Integer.parseInt(req.getParameter("eng")));
		dto.setMat(Integer.parseInt(req.getParameter("mat")));
		
		try {
			dao.updateScore(dto);
		} catch (Exception e) {
		}
		
		String page = req.getParameter("page");
		
		String cp=req.getContextPath();
		resp.sendRedirect(cp+"/score/list.do?page="+page);
	}
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String hak = req.getParameter("hak");
		String page = req.getParameter("page");
		
		ScoreDAO dao = new ScoreDAO();
		
		try {
			dao.deleteScore(hak);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String cp=req.getContextPath();
		resp.sendRedirect(cp+"/score/list.do?page="+page);
	}
	
	
}
