package com.bbs;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.MyUtil;

@WebServlet("/bbs/*")
public class BoardServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private BoardDAO dao = new BoardDAOImpl(); // up casting
	private MyUtil util = new MyUtil();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(path);
		rd.forward(req, resp);
	}
	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI(); // cp부터 끝까지 주소
		
		if(uri.indexOf("list.do") != -1) {
			//글리스트
			list(req, resp);
		}else if(uri.indexOf("created.do") != -1) {
			//글쓰기폼
			createdForm(req, resp);
		}else if(uri.indexOf("created_ok.do") != -1) {
			//글등록
			createdSubmit(req, resp);
		}else if(uri.indexOf("article.do") != -1) {
			//글보기
			article(req, resp);
		}else if(uri.indexOf("update.do") != -1) {
			//글수정폼
			updateForm(req, resp);
		}else if(uri.indexOf("update_ok.do") != -1) {
			//글수정완료
			updateSubmit(req, resp);
		}else if(uri.indexOf("delete.do") != -1) {
			//글삭제
			delete(req, resp);
		}
	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
		
		String page = req.getParameter("page");
		int current_page = 1; // 현재 출력할 페이지
		if(page != null)
			current_page = Integer.parseInt(page);
		
		//검색
		String condition = req.getParameter("condition");
		String keyword = req.getParameter("keyword");
		if(condition == null) {
			condition = "subject";
			keyword="";
		}
		if(req.getMethod().equalsIgnoreCase("GET")) {
			keyword = URLDecoder.decode(keyword,"UTF-8");
		}
		
		//전체데이터개수
		int dataCount;
		if(keyword.length() == 0)
			dataCount = dao.dataCount();
		else
			dataCount = dao.dataCount(condition, keyword); // 검색에서
		
		int rows = 2; // 한페이지 출력할 데이터 개수
		int total_page = util.pageCount(rows, dataCount); 
		if(current_page > total_page)
			current_page = total_page;
		
		int start = (current_page-1) * rows + 1;
		int end = current_page * rows;
		
		List<BoardDTO> list;
		if(keyword.length() == 0)
			list = dao.listBoard(start, end);
		else
			list = dao.listBoard(start, end, condition, keyword); // 검색
		
		//리스트 글번호
		int listNum, n=0;
		for(BoardDTO dto : list) {
			listNum = dataCount - (start + n - 1);
			dto.setListNum(listNum);
			n++;
		}
		
		String query = "";
		if(keyword.length() != 0) {
			query = "condition=" + condition + "&keyword="+
					URLEncoder.encode(keyword, "UTF-8");
		}
		
		// 페이징 처리
		String listUrl = cp + "/bbs/list.do"; // 글리스트 주소
		String articleUrl = cp + "/bbs/article.do?page="+ current_page; // 글보기 주소
		if(query.length() != 0) {
			listUrl += "?" + query;
			articleUrl += "&" + query;
		}
		String paging = util.paging(current_page, total_page, listUrl);
		
		//포워딩하는 JSP에 넘길 데이터
		req.setAttribute("list", list);
		req.setAttribute("page", current_page);
		req.setAttribute("dataCount", dataCount);
		req.setAttribute("total_page", total_page);
		req.setAttribute("articleUrl", articleUrl);
		req.setAttribute("paging", paging);
		req.setAttribute("condition", condition);
		req.setAttribute("keyword", keyword);
		
		//포워딩
		String path = "/WEB-INF/views/bbs/list.jsp";
		forward(req, resp, path);
	}
	
	protected void createdForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mode", "created");
		String path = "/WEB-INF/views/bbs/created.jsp";
		forward(req, resp, path);
	}
	
	protected void createdSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//글저장
		BoardDTO dto = new BoardDTO();
		dto.setName(req.getParameter("name"));
		dto.setSubject(req.getParameter("subject"));
		dto.setContent(req.getParameter("content"));
		dto.setPwd(req.getParameter("pwd"));
		dto.setIpAddr(req.getRemoteAddr());
		
		dao.insertBoard(dto);
		
		//리스트로 리다이렉트
		String cp = req.getContextPath();
		resp.sendRedirect(cp + "/bbs/list.do");
	}
	
	protected void article(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = Integer.parseInt(req.getParameter("num"));
		String page = req.getParameter("page");
		String condition = req.getParameter("condition");
		String keyword = req.getParameter("keyword");
		
		if(condition == null) {
			condition = "subject";
			keyword = "";
		}
		keyword = URLDecoder.decode(keyword, "utf-8");
		
		String query = "page="+page;
		if(keyword.length() != 0) {
			query += "&condition="+condition
					+"&keyword="+URLEncoder.encode(keyword, "utf-8");
		}
		
		dao.updateHitCount(num); // 조회수 증가
		BoardDTO dto = dao.readBoard(num);
		if(dto == null) {
			String cp = req.getContextPath();
			resp.sendRedirect(cp+"/bbs/list.do?"+query);
			return;
		}
		
		// 스타일 : style="white-space:pre;"
		dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
		
		BoardDTO preReadDTO = dao.preReadBoard(num, condition, keyword); // 이전글
		BoardDTO nextReadDTO = dao.nextReadBoard(num, condition, keyword); // 다음글
		
		req.setAttribute("dto", dto);
		req.setAttribute("preReadDTO", preReadDTO);
		req.setAttribute("nextReadDTO", nextReadDTO);
		req.setAttribute("query", query);
		req.setAttribute("page", page);
		
		forward(req, resp, "/WEB-INF/views/bbs/article.jsp");
	}
	
	protected void updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = Integer.parseInt(req.getParameter("num"));
		String page = req.getParameter("page");
		
		BoardDTO dto = dao.readBoard(num);
		
		if(dto == null) {
			String cp = req.getContextPath();
			resp.sendRedirect(cp+"/bbs/list.do?page="+page);
			return;
		}
		
		req.setAttribute("dto", dto);
		req.setAttribute("page", page);
		req.setAttribute("mode", "update");
		
		forward(req, resp, "/WEB-INF/views/bbs/created.jsp");
	}
	
	protected void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardDTO dto = new BoardDTO();
		dto.setNum(Integer.parseInt(req.getParameter("num")));
		dto.setName(req.getParameter("name"));
		dto.setSubject(req.getParameter("subject"));
		dto.setContent(req.getParameter("content"));
		dto.setPwd(req.getParameter("pwd"));
		
		dao.updateBoard(dto);
		
		String page = req.getParameter("page");
		
		String cp = req.getContextPath();
		resp.sendRedirect(cp+"/bbs/article.do?num="+dto.getNum()+"&page="+page);
		//resp.sendRedirect(cp+"/bbs/list.do?&page="+page);
		
	}
	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = Integer.parseInt(req.getParameter("num"));
		String page = req.getParameter("page");
		String condition = req.getParameter("condition");
		String keyword = req.getParameter("keyword");
		
		if(condition == null) {
			condition = "subject";
			keyword = "";
		}
		keyword = URLDecoder.decode(keyword, "utf-8");
		
		String query = "page="+page;
		if(keyword.length() != 0) {
			query += "&condition="+condition
					+"&keyword="+URLEncoder.encode(keyword, "utf-8");
		}
		
		dao.deleteBoard(num);
		
		String cp = req.getContextPath();
		resp.sendRedirect(cp+"/bbs/list.do?"+query);
	}
}
