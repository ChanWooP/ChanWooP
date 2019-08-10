package com.bbs;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.user.SessionInfo;
import com.util.FileManager;
import com.util.MyServlet;
import com.util.MyUtil;


@WebServlet("/bbs/*")
public class BoardServlet extends MyServlet {
	private static final long serialVersionUID = 1L;
	private String pathname;
	
	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String uri = req.getRequestURI();
		
		HttpSession session = req.getSession();
		
		String root = session.getServletContext().getRealPath("/");
		pathname = root+"uploads"+File.separator+"gallary";
		File f = new File(pathname);
		if(! f.exists())
			f.mkdirs();

		SessionInfo info = (SessionInfo)session.getAttribute("user");
		
		if(info == null) {
			forward(req, resp, "/WEB-INF/views/login/login.jsp");
			return;
		}

		if(uri.indexOf("list.do")!=-1) {
			list(req, resp);
		}else if(uri.indexOf("created.do")!=-1) {
			createdForm(req, resp);
		}else if(uri.indexOf("created_ok.do")!=-1) {
			createdSubmit(req, resp);
		}else if(uri.indexOf("article.do")!=-1) {
			article(req, resp);
		} else if(uri.indexOf("delete.do")!=-1) {
			delete(req, resp);
		} else if(uri.indexOf("update.do")!=-1) {
			updateForm(req, resp);
		}else if(uri.indexOf("update_ok.do")!=-1) {
			updateSubmit(req, resp);
		}
	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
		BoardDAO dao = new BoardDAO();
		MyUtil util = new MyUtil();
		String page = req.getParameter("page");
		int current_page = 1;
		if(page!=null)
			current_page = Integer.parseInt(page);

		
		int dataCount;
		dataCount= dao.dataCount();
		
		int rows = 10;
		int total_page = util.pageCount(rows, dataCount);
		
		if(current_page > total_page)
			current_page = total_page;
		
		int start = (current_page-1)*rows+1;
		int end = current_page*rows;
		
		List<BoardDTO> list = null;
		list=dao.listBoard(start, end);
		
		int listNum, n=0;
		for(BoardDTO dto : list) {
			listNum=dataCount-(start+n-1);
			dto.setListNum(listNum);
			n++;
		}
		String listUrl=cp+"/bbs/list.do";
		String articleUrl=cp+"/bbs/article.do?page="+current_page;
		String paging=util.paging(current_page, total_page, listUrl);
		
		req.setAttribute("list", list);
		req.setAttribute("page", current_page);
		req.setAttribute("total_page", total_page);
		req.setAttribute("dataCount", dataCount);
		req.setAttribute("articleUrl", articleUrl);
		req.setAttribute("paging", paging);
		
		forward(req, resp, "/WEB-INF/views/bbs/list.jsp");
	}
	
	protected void createdForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("mode", "created");
		forward(req, resp, "/WEB-INF/views/bbs/created.jsp");
	}
	
	protected void createdSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
		BoardDAO dao = new BoardDAO();
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("user");
		BoardDTO dto=new BoardDTO();
		
		String encType="utf-8";
		int maxFilesize = 5*1024*1024;
		MultipartRequest mreq;
		mreq = new MultipartRequest(req, pathname, maxFilesize, encType
				, new DefaultFileRenamePolicy());
		
		dto.setUserId(info.getUserId());
		dto.setSubject(mreq.getParameter("subject"));
		dto.setContent_(mreq.getParameter("content"));
		String saveName = FileManager.doFilerename(pathname, mreq.getFilesystemName("upload"));
		dto.setGalleryimage(saveName);
		
		dao.insertBoard(dto);
		
		resp.sendRedirect(cp+"/bbs/list.do");
	}
	
	private void article(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글보기
		MyUtil util = new MyUtil();
		String cp = req.getContextPath();
		BoardDAO dao = new BoardDAO();
		String boardId=req.getParameter("boardId");
		String page=req.getParameter("page");
		String query = "page="+page;
		
		// 조회수 증가
		dao.updateHitCount(boardId);
		
		// 게시물 가져오기
		BoardDTO dto = dao.readBoard(boardId);
		if(dto==null) { // 게시물이 없으면 다시 리스트로
			resp.sendRedirect(cp+"/bbs/list.do");
			return;
		}
		dto.setContent_(util.htmlSymbols(dto.getContent_()));
		
		// JSP로 전달할 속성
		req.setAttribute("dto", dto);
		req.setAttribute("page", page);
		req.setAttribute("query", query);
		// 포워딩
		forward(req, resp, "/WEB-INF/views/bbs/article.jsp");
	}
	
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 삭제
		String cp = req.getContextPath();
		BoardDAO dao = new BoardDAO();
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("user");

		String page=req.getParameter("page");
		String boardId=req.getParameter("boardId");

		int result = dao.deleteBoard(boardId, info.getUserId());
		
		if(result==0) {
			resp.sendRedirect(cp+"/bbs/list.do?page="+page);
		}else {
			FileManager.doFiledelete(pathname, req.getParameter("image"));
			resp.sendRedirect(cp+"/bbs/list.do?page="+page);
		}
	
	}
	
	private void updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정 폼
		String cp = req.getContextPath();
		BoardDAO dao = new BoardDAO();
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("user");

		String page=req.getParameter("page");
		String boardId=req.getParameter("boardId");
		BoardDTO dto=dao.readBoard(boardId);
		
		if(dto==null) {
			resp.sendRedirect(cp+"/bbs/list.do?page="+page);
			return;
		}
		
		// 게시물을 올린 사용자가 아니면
		if(! dto.getUserId().equals(info.getUserId())) {
			resp.sendRedirect(cp+"/bbs/list.do?page="+page);
			return;
		}
		
		req.setAttribute("dto", dto);
		req.setAttribute("page", page);
		req.setAttribute("mode", "update");
		
		forward(req, resp, "/WEB-INF/views/bbs/created.jsp");
	}
	
	private void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정 완료
		String cp = req.getContextPath();
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("user");
		BoardDAO dao = new BoardDAO();
		String encType="utf-8";
		int maxFilesize = 5*1024*1024;
		MultipartRequest mreq;
		mreq = new MultipartRequest(req, pathname, maxFilesize, encType
				, new DefaultFileRenamePolicy());
		
		String page=mreq.getParameter("page");
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			resp.sendRedirect(cp+"/bbs/list.do?page="+page);
			return;
		}
		
		BoardDTO dto=new BoardDTO();
		if(mreq.getFile("upload")==null) {
			dto.setGalleryimage(mreq.getParameter("image"));
		}else {
			String saveName = FileManager.doFilerename(pathname, mreq.getFilesystemName("upload"));
			dto.setGalleryimage(saveName);
			FileManager.doFiledelete(pathname, mreq.getParameter("image"));
		}
		dto.setBoardId(mreq.getParameter("boardId"));
		dto.setSubject(mreq.getParameter("subject"));
		dto.setContent_(mreq.getParameter("content"));
		
		dao.updateBoard(dto, info.getUserId());
		
		resp.sendRedirect(cp+"/bbs/list.do?page="+page);
	}
}
