package com.notice;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.SessionInfo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.FileManager;
import com.util.MyServlet;
import com.util.MyUtil;

@WebServlet("/notice/*")
public class NoticeServlet extends MyServlet {
	private static final long serialVersionUID = 1L;

	private String pathname;
	
	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uri=req.getRequestURI();
		
		HttpSession session=req.getSession();
		
		// ������ ������ ���(pathname)
		String root = session.getServletContext().getRealPath("/");
		pathname = root+"uploads"+File.separator+"notice";
		File f = new File(pathname);
		if(! f.exists()) {
			f.mkdirs();
		}
		
		// uri�� ���� �۾� ����
		if(uri.indexOf("list.do")!=-1) {
			list(req, resp);
		} else if(uri.indexOf("created.do")!=-1) {
			createdForm(req, resp);
		}  else if(uri.indexOf("created_ok.do")!=-1) {
			createdSubmit(req, resp);
		}  else if(uri.indexOf("article.do")!=-1) {
			article(req, resp);
		} else if(uri.indexOf("update.do")!=-1) {
			updateForm(req, resp);
		} else if(uri.indexOf("update_ok.do")!=-1) {
			updateSubmit(req, resp);
		} else if(uri.indexOf("deleteFile.do")!=-1) {
			deleteFile(req, resp);
		} else if(uri.indexOf("delete.do")!=-1) {
			delete(req, resp);
		} else if(uri.indexOf("download.do")!=-1) {
			download(req, resp);
		} else if(uri.indexOf("deleteList.do")!=-1){
			deleteList(req, resp);
		}
	}

	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �Խù� ����Ʈ
		NoticeDAO dao=new NoticeDAO();
		MyUtil util=new MyUtil();		
		String cp=req.getContextPath();
		
		String page=req.getParameter("page");
		int current_page=1;
		if(page!=null)
			current_page=Integer.parseInt(page);
		
		String condition=req.getParameter("condition");
		String keyword=req.getParameter("keyword");
		if(condition==null) {
			condition="subject";
			keyword="";
		}
		if(req.getMethod().equalsIgnoreCase("GET")) {
			keyword=URLDecoder.decode(keyword,"utf-8");
		}
		
		int rows = 10; // �������� ǥ���� ������ ����
        String numPerPage=req.getParameter("rows");
        if(numPerPage!=null)
            rows=Integer.parseInt(numPerPage);
		int dataCount, total_page;
		
		if(keyword.length()!=0)
			dataCount= dao.dataCount(condition, keyword);
		else
			dataCount= dao.dataCount();
		total_page=util.pageCount(rows, dataCount);
		
		if(current_page>total_page)
			current_page=total_page;
		
		int start=(current_page-1)*rows+1;
		int end=current_page*rows;
		
		List<NoticeDTO> list;
		if(keyword.length()!=0)
			list= dao.listNotice(start, end, condition, keyword);
		else
			list= dao.listNotice(start, end);
		
		// ������
		List<NoticeDTO> listNotice=null;
		listNotice = dao.listNotice();
		for(NoticeDTO dto : listNotice){
			dto.setCreated(dto.getCreated().substring(0, 10));
		}
		
		long gap;
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// ����Ʈ �۹�ȣ �����
		int listNum, n=0;
		for(NoticeDTO dto : list){
			listNum=dataCount-(start+n-1);
			dto.setListNum(listNum);
			try {
				Date date = sdf.parse(dto.getCreated());
				
				//gap = (curDate.getTime() - date.getTime())/(1000*60*60*24); // ����
				gap = (curDate.getTime() - date.getTime())/(1000*60*60); // �ð�
				dto.setGap(gap);
				
			} catch (Exception e) {
			}
		
			
			dto.setCreated(dto.getCreated().substring(0, 10));
			n++;
		}
		
		String query="";
		String listUrl;
		String articleUrl;
		
		listUrl=cp+"/notice/list.do?rows="+rows;
		articleUrl=cp+"/notice/article.do?page=" +current_page+"&rows="+rows;
		if(keyword.length()!=0) {
			query="condition="+condition+"&keyword="+URLEncoder.encode(keyword,"utf-8");
			
			listUrl += "&"+query;
			articleUrl += "&"+query;
		}
		
		String paging=util.paging(current_page, total_page, listUrl);
		
		// ������ jsp�� �ѱ� ������
		req.setAttribute("list", list);
		req.setAttribute("listNotice", listNotice);
		req.setAttribute("articleUrl", articleUrl);
		req.setAttribute("dataCount", dataCount);
		req.setAttribute("page", current_page);
		req.setAttribute("total_page", total_page);
		req.setAttribute("paging", paging);
		req.setAttribute("condition", condition);
		req.setAttribute("keyword", keyword);
		req.setAttribute("rows", rows);
		
		// JSP�� ������
		forward(req, resp, "/WEB-INF/views/notice/list.jsp");
	}
	
	private void createdForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �۾��� ��
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		String cp=req.getContextPath();
		String rows=req.getParameter("rows");
		
		if(info==null) {
			resp.sendRedirect(cp+"/member/login.do");
			return;
		}
		
		
		
		// admin�� ���� ���
		if(! info.getUserId().equals("admin")) {
			resp.sendRedirect(cp+"/notice/list.do?rows="+rows);
			return;
		}
		
		req.setAttribute("mode", "created");
		req.setAttribute("rows", rows);
		forward(req, resp, "/WEB-INF/views/notice/created.jsp");
	}
	
	private void createdSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �� ����
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		NoticeDAO dao=new NoticeDAO();
		String cp=req.getContextPath();
		
		if(info==null) {
			resp.sendRedirect(cp+"/member/login.do");
			return;
		}
		
		String encType = "utf-8";
		int maxFilesize = 5*1024*1024;
		MultipartRequest mreq;
		mreq = new MultipartRequest(req, pathname, maxFilesize, encType
				, new DefaultFileRenamePolicy());
		
		String rows=mreq.getParameter("rows");
		
		// admin�� ���� ���
		if(! info.getUserId().equals("admin")) {
			resp.sendRedirect(cp+"/notice/list.do?rows="+rows);
			return;
		}
		
	    NoticeDTO dto=new NoticeDTO();
	    dto.setUserId(info.getUserId());
	    if(mreq.getParameter("notice")!=null)
	    	dto.setNotice(Integer.parseInt(mreq.getParameter("notice")));
	    dto.setSubject(mreq.getParameter("subject"));
	    dto.setContent(mreq.getParameter("content"));
	    
	    if(mreq.getFile("upload") != null) { // ÷�������� �����ϸ�
	    	dto.setSaveFilename(mreq.getFilesystemName("upload"));
	    	dto.setOriginalFilename(mreq.getFilesystemName("upload"));
	    	dto.setFileSize(mreq.getFile("upload").length());
	    }
	    
	    dao.insertNotice(dto);
		
		resp.sendRedirect(cp+"/notice/list.do?rows="+rows);
	}

	private void article(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �ۺ���
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		NoticeDAO dao=new NoticeDAO();
		String cp=req.getContextPath();
		
		if(info==null) {
			resp.sendRedirect(cp+"/member/login.do");
			return;
		}
		
		int num=Integer.parseInt(req.getParameter("num"));
		String page=req.getParameter("page");
		String rows=req.getParameter("rows");
		
		String condition=req.getParameter("condition");
		String keyword=req.getParameter("keyword");
		if(condition==null) {
			condition="subject";
			keyword="";
		}
		keyword=URLDecoder.decode(keyword, "utf-8");

		String query="page="+page+"&rows="+rows;
		if(keyword.length()!=0) {
			query+="&condition="+condition+"&keyword="+URLEncoder.encode(keyword, "UTF-8");
		}
		
		// ��ȸ��
		dao.updateHitCount(num);
		
		// �Խù� ��������
		NoticeDTO dto=dao.readNotice(num);
		if(dto==null) {
			resp.sendRedirect(cp+"/notice/list.do?"+query);
			return;
		}
		
		dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
		
		// ������/������
		NoticeDTO preReadDto = dao.preReadNotice(dto.getNum(), condition, keyword);
		NoticeDTO nextReadDto = dao.nextReadNotice(dto.getNum(), condition, keyword);
		
		req.setAttribute("dto", dto);
		req.setAttribute("preReadDto", preReadDto);
		req.setAttribute("nextReadDto", nextReadDto);
		req.setAttribute("query", query);
		req.setAttribute("page", page);
		req.setAttribute("rows", rows);
		
		forward(req, resp, "/WEB-INF/views/notice/article.jsp");
	}
	
	private void updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���� ��
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		NoticeDAO dao=new NoticeDAO();
		String cp=req.getContextPath();
		
		if(info==null) {
			resp.sendRedirect(cp+"/member/login.do");
			return;
		}
		
		String page=req.getParameter("page");
		String rows=req.getParameter("rows");
		int num=Integer.parseInt(req.getParameter("num"));
		
		NoticeDTO dto=dao.readNotice(num);
		if(dto==null) {
			resp.sendRedirect(cp+"/notice/list.do?page="+page+"&rows="+rows);
			return;
		}
		
		// ���� ����� ����� ���� ����
		if(! info.getUserId().equals(dto.getUserId())) {
			resp.sendRedirect(cp+"/notice/list.do?page="+page+"&rows="+rows);
			return;
		}
		
		req.setAttribute("dto", dto);
		req.setAttribute("page", page);
		req.setAttribute("rows", rows);
		
		req.setAttribute("mode", "update");
		
		forward(req, resp, "/WEB-INF/views/notice/created.jsp");
	}

	private void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���� �Ϸ�
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");

		NoticeDAO dao=new NoticeDAO();
		String cp=req.getContextPath();
		
		if(info==null) {
			resp.sendRedirect(cp+"/member/login.do");
			return;
		}
		
		String encType = "utf-8";
		int maxFilesize = 5*1024*1024;
		MultipartRequest mreq;
		mreq = new MultipartRequest(req, pathname, maxFilesize, encType
				, new DefaultFileRenamePolicy());
		
		NoticeDTO dto=new NoticeDTO();
		
		int num=Integer.parseInt(mreq.getParameter("num"));
		String page=mreq.getParameter("page");
		String rows=mreq.getParameter("rows");
		
		dto.setNum(num);
	    if(mreq.getParameter("notice")!=null)
	    	dto.setNotice(Integer.parseInt(mreq.getParameter("notice")));
		dto.setSubject(mreq.getParameter("subject"));
		dto.setContent(mreq.getParameter("content"));
		dto.setSaveFilename(mreq.getParameter("saveFilename"));
		dto.setOriginalFilename(mreq.getParameter("originalFilename"));
		dto.setFileSize(Long.parseLong(mreq.getParameter("fileSize")));
		
		if(mreq.getFile("upload")!=null) {
			// �������� ����
			FileManager.doFiledelete(pathname, mreq.getParameter("saveFilename"));
			
			//���ο� ����
			dto.setSaveFilename(mreq.getFilesystemName("upload"));
			dto.setOriginalFilename(mreq.getOriginalFileName("upload"));
			dto.setFileSize(mreq.getFile("upload").length());
		}
		
		dao.updateNotice(dto);
		
		resp.sendRedirect(cp+"/notice/list.do?page="+page+"&rows="+rows);
	}

	private void deleteFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���� ����
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		NoticeDAO dao=new NoticeDAO();
		String cp=req.getContextPath();
	
		int num=Integer.parseInt(req.getParameter("num"));
		String page=req.getParameter("page");
		String rows = req.getParameter("rows");
		
		NoticeDTO dto=dao.readNotice(num);
		if(dto==null) {
			resp.sendRedirect(cp+"/notice/list.do?page="+page+"&rows"+rows);
			return;
		}
		
		if(info==null || ! info.getUserId().equals(dto.getUserId())) {
			resp.sendRedirect(cp+"/notice/list.do?page="+page+"&rows"+rows);
			return;
		}
		
		//���� ����
		FileManager.doFiledelete(pathname, dto.getSaveFilename());
		//���ϸ�� ����ũ�� ����
		dto.setOriginalFilename("");
		dto.setSaveFilename("");
		dto.setFileSize(0);
		dao.updateNotice(dto);
		
		req.setAttribute("dto", dto);
		req.setAttribute("page", page);
		req.setAttribute("rows", rows);
		
		req.setAttribute("mode", "update");

		forward(req, resp, "/WEB-INF/views/notice/created.jsp");			
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ����
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		NoticeDAO dao=new NoticeDAO();
		String cp=req.getContextPath();
		
		if(info==null) {
			resp.sendRedirect(cp+"/member/login.do");
			return;
		}
		
		int num=Integer.parseInt(req.getParameter("num"));
		String page=req.getParameter("page");
		String rows=req.getParameter("rows");
		String condition=req.getParameter("condition");
		String keyword=req.getParameter("keyword");
		if(condition==null) {
			condition="subject";
			keyword="";
		}
		keyword=URLDecoder.decode(keyword, "utf-8");

		String query="page="+page+"&rows="+rows;
		if(keyword.length()!=0) {
			query+="&condition="+condition+"&keyword="+URLEncoder.encode(keyword, "UTF-8");
		}

		NoticeDTO dto=dao.readNotice(num);
		if(dto==null) {
			resp.sendRedirect(cp+"/notice/list.do?"+query);
			return;
		}
		
		// ���� ����� ���, admin �� ���� ����
		if(! info.getUserId().equals(dto.getUserId()) && ! info.getUserId().equals("admin")) {
			resp.sendRedirect(cp+"/notice/list.do?"+query);
			return;
		}
		
		if(dto.getSaveFilename()!=null && dto.getSaveFilename().length()!=0)
			FileManager.doFiledelete(pathname, dto.getSaveFilename());
		
		dao.deleteNotice(num);
		
		resp.sendRedirect(cp+"/notice/list.do?"+query);
	}

	private void download(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���� �ٿ�ε�
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		String cp = req.getContextPath();
		
		if(info == null) {
			resp.sendRedirect(cp+"/member/login.do");
			return;
		}
		
		NoticeDAO dao = new NoticeDAO();
		int num = Integer.parseInt(req.getParameter("num"));
		
		NoticeDTO dto = dao.readNotice(num);
		boolean b = false;
		if(dto!=null) {
			b = FileManager.doFiledownload(dto.getSaveFilename()
					, dto.getOriginalFilename(), pathname, resp);
		}
		
		if(! b) {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print("<script>alert('���ϴٿ�ε尡 ���� �߽��ϴ�.'); history.back();</script>");
		}
	}
	
	private void deleteList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		String cp = req.getContextPath();
		
		if(info == null) {
			resp.sendRedirect(cp+"/member/login.do");
			return;
		}
		
		if(! info.getUserId().equals("admin")) {
			resp.sendRedirect(cp+"/notice/list.do");
			return;
		}
		
		String []s = req.getParameterValues("nums");
		int nums[] = null;
		nums = new int[s.length];
		for(int a=0; a<s.length; a++)
			nums[a] = Integer.parseInt(s[a]);
		
		String page = req.getParameter("page");
		String condition = req.getParameter("condition");
		String keyword = req.getParameter("keyword");
		String rows = req.getParameter("rows");
		
		NoticeDAO dao = new NoticeDAO();
		dao.deleteBoardList(nums);
		
		String query = "rows="+rows+"&page="+page;
		if(keyword!=null && keyword.length()!=0) {
			query += "&condition="+condition+"&keyword="+URLEncoder.encode(keyword, "utf-8");
		}
		
		resp.sendRedirect(cp+"/notice/list.do?"+query);
		
	}
}
