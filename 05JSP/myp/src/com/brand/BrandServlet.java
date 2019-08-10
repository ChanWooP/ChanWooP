package com.brand;

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

@WebServlet("/brand/*")
public class BrandServlet extends MyServlet{
	private static final long serialVersionUID = 1L;
	
	private String pathname;
	
	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String uri = req.getRequestURI();
		
		HttpSession session = req.getSession();
		
		String root = session.getServletContext().getRealPath("/");
		pathname = root+"uploads"+File.separator+"brand";
		File f = new File(pathname);
		if(! f.exists())
			f.mkdirs();
		
		if(uri.indexOf("addBrand.do")!=-1) {
			addBrand(req, resp);
		}else if(uri.indexOf("list.do")!=-1) {
			list(req, resp);
		}else if(uri.indexOf("delBrand.do")!=-1) {
			delBrand(req,resp);
		}else if(uri.indexOf("upBrand.do")!=-1) {
			upBrand(req, resp);
		}
	}
	
	protected void addBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("user");
		
		BrandDAO dao = new BrandDAO();
		String cp = req.getContextPath();
		
		if(info==null) {
			resp.sendRedirect(cp);
			return;
		}
		
		String encType="utf-8";
		int maxFilesize = 5*1024*1024;
		MultipartRequest mreq;
		mreq = new MultipartRequest(req, pathname, maxFilesize, encType
				, new DefaultFileRenamePolicy());
		
		if(! info.getUserId().equals("admin")) {
			resp.sendRedirect(cp);
			return;
		}
		
		BrandDTO dto = new BrandDTO();
		dto.setBrandName(mreq.getParameter("addBranName"));
		String saveName = FileManager.doFilerename(pathname, mreq.getFilesystemName("upload"));
		dto.setBrandImage(saveName);
		
		dao.insertBrand(dto);
		
		resp.sendRedirect(cp+"/brand/list.do");
		
		
	}
	
	protected void upBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("user");
		
		BrandDAO dao = new BrandDAO();
		String cp = req.getContextPath();
		
		if(info==null) {
			resp.sendRedirect(cp);
			return;
		}
		
		String encType="utf-8";
		int maxFilesize = 5*1024*1024;
		MultipartRequest mreq;
		mreq = new MultipartRequest(req, pathname, maxFilesize, encType
				, new DefaultFileRenamePolicy());
		
		if(! info.getUserId().equals("admin")) {
			resp.sendRedirect(cp);
			return;
		}
		
		BrandDTO dto = new BrandDTO();
		dto.setBrandId(mreq.getParameter("upBrandId"));
		dto.setBrandName(mreq.getParameter("upBranName"));
		if(mreq.getFile("upload")==null) {
			dto.setBrandImage(mreq.getParameter("upBrandImage"));
		}else {
			String saveName = FileManager.doFilerename(pathname, mreq.getFilesystemName("upload"));
			dto.setBrandImage(saveName);
			FileManager.doFiledelete(pathname, mreq.getParameter("upBrandImage"));
		}
		dao.updateBrand(dto);
		
		resp.sendRedirect(cp+"/brand/list.do");
		
		
	}
	
	protected void delBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp = req.getContextPath();
		BrandDAO dao = new BrandDAO();
		BrandDTO dto = dao.readBrand(req.getParameter("num"));

		int result = dao.deleteBrand(req.getParameter("num"));
		
		
		if(result==0) {
			resp.sendRedirect(cp+"/brand/list.do");
		}else {
			FileManager.doFiledelete(pathname, dto.getBrandImage());
			resp.sendRedirect(cp+"/brand/list.do");
		}

	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BrandDAO dao = new BrandDAO();
		
		List<BrandDTO> list = dao.list();
		
		req.setAttribute("list", list);
		
		forward(req, resp, "/WEB-INF/views/brand/brand_list.jsp");
	}
}
