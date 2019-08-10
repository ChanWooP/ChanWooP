package com.board;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.BoardDTO;
import com.member.SessionInfo;
import com.util.MyServlet;
import com.util.MyUtil;

@WebServlet("/board/*")
public class BoardServlet extends MyServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		if(info == null) {
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
			return;
		}
		
		String uri = req.getRequestURI();
		
		if(uri.indexOf("list.do") != -1) {
			list(req, resp);
		}else if(uri.indexOf("created.do") != -1) {
			createdFrom(req, resp);
		}else if(uri.indexOf("created_ok.do") != -1) {
			createdSubmit(req, resp);
		}else if(uri.indexOf("article.do") != -1) {
			article(req, resp);
		}else if(uri.indexOf("update.do") != -1) {
			updateForm(req, resp);
		}else if(uri.indexOf("update_ok.do") != -1) {
			updateSubmit(req, resp);
		}else if(uri.indexOf("reply.do") != -1) {
			replyForm(req, resp);
		}else if(uri.indexOf("reply_ok.do") != -1) {
			replySubmit(req, resp);
		}else if(uri.indexOf("delete.do") != -1) {
			delete(req, resp);
		}
	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
		MyUtil util = new MyUtil();
		BoardDAO dao = new BoardDAO();
		
		String page = req.getParameter("page");
		int current_page = 1;
		if(page != null)
			current_page = Integer.parseInt(page);
		
		//검색
		String condition = req.getParameter("condition");
		String keyword = req.getParameter("keyword");
		if(condition == null) {
			condition= "subject";
			keyword = "";
		}
		
		//GET 방식인 경우 디코딩
		if(req.getMethod().equalsIgnoreCase("GET")) {
			keyword = URLDecoder.decode(keyword, "utf-8");
		}
		
		//전체 데이터 개수
		int dataCount;
		if(keyword.length() == 0) 
			dataCount = dao.dataCount();
		else
			dataCount = dao.dataCount(condition, keyword);
		
		//전체 페이지 수
		int rows = 10;
		int total_page = util.pageCount(rows, dataCount);
		
		if(current_page > total_page)
			current_page = total_page;
		
		// 게시물 가져올 시작과 끝
		int start = (current_page -1) * rows+1;
		int end = current_page * rows;
		
		//게시물 가져오기
		List<BoardDTO> list = null;
		if(keyword.length() == 0)
			list = dao.listBoard(start, end);
		else
			list = dao.listBoard(start, end, condition, keyword);
		
		//리스트 글번호 만들기
		int listNum, n=0;
		for(BoardDTO dto : list) {
			listNum = dataCount-(start+n-1);
			dto.setListNum(listNum);
			n++;
		}
		
		String query = "";
		if(keyword.length() != 0) {
			query="condition="+condition+ "&keyword="+URLEncoder.encode(keyword, "utf-8");
		}
		
		// 페이징 처리
		String listUrl=cp+"/board/list.do";
		String articleUrl=cp+"/board/article.do?page="+current_page;
		if(query.length()!=0) {
			listUrl+="?"+query;
			articleUrl+="&"+query;
		}
		
		String paging=util.paging(current_page, total_page, listUrl);
		
		// 포워딩할 JSP로 넘길 속성
		req.setAttribute("list", list);
		req.setAttribute("page", current_page);
		req.setAttribute("total_page", total_page);
		req.setAttribute("dataCount", dataCount);
		req.setAttribute("articleUrl", articleUrl);
		req.setAttribute("paging", paging);
		req.setAttribute("condition", condition);
		req.setAttribute("keyword", keyword);
		
		forward(req, resp, "/WEB-INF/views/board/list.jsp");
	}
	
	protected void createdFrom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mode", "created");
		forward(req, resp, "/WEB-INF/views/board/created.jsp");
	}
	
	protected void createdSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		dto.setUserId(info.getUserId());
		dto.setSubject(req.getParameter("subject"));
		dto.setContent(req.getParameter("content"));
		
		dao.insertBoard(dto, "created");
		
		resp.sendRedirect(cp+"/board/list.do");
	}
	
	protected void article(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
		
		int num = Integer.parseInt(req.getParameter("boardNum"));
		String page = req.getParameter("page");
		String condition = req.getParameter("condition");
		String keyword = req.getParameter("keyword");
		BoardDAO dao = new BoardDAO();
		MyUtil util = new MyUtil();
		
		if(condition == null) {
			condition = "subject";
			keyword= "";
		}
		keyword = URLDecoder.decode(keyword,"utf-8");
		
		String query = "page="+page;
		if(keyword.length() != 0) {
			query+="&condition="+condition+"&keyword="+URLEncoder.encode(keyword, "utf-8");
		}
		
		dao.updateHitCount(num);
		
		BoardDTO dto = dao.readBoard(num);
		if(dto == null) {
			resp.sendRedirect(cp+"/bbs/list.do?"+query);
			return;
		}
		dto.setContent(util.htmlSymbols(dto.getContent()));
		
		BoardDTO preReadDto=dao.preReadBoard(dto.getGroupNum(), dto.getOrderNo(), condition, keyword);
		BoardDTO nextReadDto=dao.nextReadBoard(dto.getGroupNum(), dto.getOrderNo(), condition, keyword);
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("page", page);
		req.setAttribute("query", query);
		req.setAttribute("preReadDto", preReadDto);
		req.setAttribute("nextReadDto", nextReadDto);
		
		
		forward(req, resp, "/WEB-INF/views/board/article.jsp");
	}
	
	protected void updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	protected void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	protected void replyForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 답변은 게시글을 올린사람과 관리자만 가능
		BoardDAO dao = new BoardDAO();
		String cp = req.getContextPath();
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		String page = req.getParameter("page");
		
		BoardDTO dto = dao.readBoard(boardNum);
		if(dto==null || (! dto.getUserId().equals(info.getUserId())&&! info.getUserId().equals("admin"))) {
			resp.sendRedirect(cp+"/board/list.do?page="+page);
			return;
		}
		
		String s = "["+dto.getSubject()+"]에 대한 답변입니다.\n";
		dto.setContent(s);
		
		req.setAttribute("dto", dto);
		req.setAttribute("mode", "reply");
		req.setAttribute("page", page);
		
		forward(req, resp, "/WEB-INF/views/board/created.jsp");
	}
	
	protected void replySubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp+"/board/list.do");
			return;
		}
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		dto.setSubject(req.getParameter("subject"));
		dto.setContent(req.getParameter("content"));
		dto.setUserId(info.getUserId());
		dto.setGroupNum(Integer.parseInt(req.getParameter("groupNum")));
		dto.setOrderNo(Integer.parseInt(req.getParameter("orderNo")));
		dto.setDepth(Integer.parseInt(req.getParameter("depth")));
		dto.setParent(Integer.parseInt(req.getParameter("parent")));
		
		dao.insertBoard(dto, "reply");
		
		String page = req.getParameter("page");
		resp.sendRedirect(cp+"/board/list.do?page="+page);
	}
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
	
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		//글쓴이 및 관리자만 삭제 가능하도록! 구현해보기
		
		BoardDAO dao = new BoardDAO();
		String page = req.getParameter("page");
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		String condition = req.getParameter("condition");
		String keyword = req.getParameter("keyword");
		if(condition == null) {
			condition = "subject";
			keyword = "";
		}
		keyword = URLDecoder.decode(keyword, "utf-8");
		
		String query = "page="+page;
		if(keyword.length()!=0) {
			query+="&condition="+condition+"&keyword="+URLEncoder.encode(keyword, "UTF-8");
		}
		
		dao.deleteBoard(boardNum);
		
		resp.sendRedirect(cp+"/board/list.do?"+query);
		
	}
	
}
