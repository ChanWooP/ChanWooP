package com.car;

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

@WebServlet("/car/*")
public class CarServlet extends MyServlet{
	private static final long serialVersionUID = 1L;
	private String pathname;
	
	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String uri = req.getRequestURI();
		
		HttpSession session = req.getSession();
		
		String root = session.getServletContext().getRealPath("/");
		pathname = root+"uploads"+File.separator+"car";
		File f = new File(pathname);
		if(! f.exists())
			f.mkdirs();
		
		if(uri.indexOf("addCar.do")!=-1) {
			addCar(req, resp);
		}else if(uri.indexOf("list.do")!=-1) {
			list(req, resp);
		}else if(uri.indexOf("delCar.do")!=-1) {
			delCar(req, resp);
		}else if(uri.indexOf("upCar.do")!=-1) {
			upCar(req, resp);
		}
	}

	protected void addCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("user");
		
		CarDAO dao = new CarDAO();
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
		
		
		CarDTO dto = new CarDTO();
		dto.setCarName(mreq.getParameter("addCarName"));
		String saveName = FileManager.doFilerename(pathname, mreq.getFilesystemName("upload"));
		dto.setCarImage(saveName);
		String brandId = mreq.getParameter("brandId");
		dto.setBrandId(brandId);
		dto.setCarContent(mreq.getParameter("addCarContent"));
		
		dao.insertCar(dto);

		resp.sendRedirect(cp+"/car/list.do?brandId="+brandId);
		
	}
	
	protected void upCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("user");
		
		CarDAO dao = new CarDAO();
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
		
		CarDTO dto = new CarDTO();
		if(mreq.getFile("upload")==null) {
			dto.setCarImage(mreq.getParameter("carImage"));
		}else {
			String saveName = FileManager.doFilerename(pathname, mreq.getFilesystemName("upload"));
			dto.setCarImage(saveName);
			FileManager.doFiledelete(pathname, mreq.getParameter("carImage"));
		}
		
		dto.setCarName(mreq.getParameter("upCarName"));
		dto.setCarId(mreq.getParameter("upCarId"));
		String brandId = mreq.getParameter("brandId");
		dto.setBrandId(brandId);
		dto.setCarContent(mreq.getParameter("upCarContent"));
		
		dao.updateCar(dto);

		resp.sendRedirect(cp+"/car/list.do?brandId="+brandId);
		
	}
	
	protected void delCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo)session.getAttribute("user");
		String cp = req.getContextPath();
		
		if(! info.getUserId().equals("admin")) {
			resp.sendRedirect(cp);
			return;
		}
		
		CarDAO dao = new CarDAO();
		String carId = req.getParameter("carId");
		String brandId = req.getParameter("brandId");
		CarDTO dto = dao.readBrand(carId);

		int result = dao.deleteCar(carId);

		if(result==0) {
			resp.sendRedirect(cp+"/car/list.do?brandId="+brandId);
		}else {
			FileManager.doFiledelete(pathname, dto.getCarImage());
			resp.sendRedirect(cp+"/car/list.do?brandId="+brandId);
		}

	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CarDAO dao = new CarDAO();
		
		List<CarDTO> list = dao.list(req.getParameter("brandId"));
		
		req.setAttribute("list", list);
		req.setAttribute("brandId", req.getParameter("brandId"));
		
		forward(req, resp, "/WEB-INF/views/car/car_list.jsp");
	}

}
