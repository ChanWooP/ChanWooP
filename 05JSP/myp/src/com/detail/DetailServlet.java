package com.detail;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.car.CarDAO;
import com.car.CarDTO;
import com.util.MyServlet;

@WebServlet("/detail/*")
public class DetailServlet extends MyServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		
		if(uri.indexOf("list.do")!=-1) {
			list(req, resp);
		}else if(uri.indexOf("addDetail.do")!=-1) {
			addDetail(req, resp);
		}else if(uri.indexOf("delDetail.do")!=-1) {
			delDetail(req, resp);
		}else if(uri.indexOf("upDetail.do")!=-1) {
			upDetail(req, resp);
		}
	}
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DetailDAO daoD = new DetailDAO();
		CarDAO daoC = new CarDAO();
		CarDTO dto = daoC.readBrand(req.getParameter("carId"));
		if(dto.getCarContent()!=null)
			dto.setCarContent(dto.getCarContent().replaceAll("\n", "<br>"));
		List<DetailDTO> list = daoD.list(req.getParameter("carId"));
		List<Map<String, String>> bodyList = daoD.bodyList();
		
		req.setAttribute("list", list);
		req.setAttribute("dto", dto);
		req.setAttribute("bodyList", bodyList);
		req.setAttribute("brandId", req.getParameter("brandId"));
		req.setAttribute("carId", req.getParameter("carId"));
		
		forward(req, resp, "/WEB-INF/views/detail/detail_list.jsp");
	}

	protected void addDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DetailDAO dao = new DetailDAO();
		DetailDTO dto = new DetailDTO();
		String cp = req.getContextPath();
		String carId = req.getParameter("carId");
		String brandId = req.getParameter("brandId");
		
		dto.setCarId(carId);
		dto.setBodyId(req.getParameter("bodyList"));
		dto.setDetailName(req.getParameter("addDetailName"));
		dto.setDetailPrice(Integer.parseInt(req.getParameter("addDetailPrice")));

		dao.insertDetail(dto);
		
		resp.sendRedirect(cp+"/detail/list.do?brandId="+brandId+"&carId="+carId);

	}
	
	protected void upDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DetailDAO dao = new DetailDAO();
		DetailDTO dto = new DetailDTO();
		String cp = req.getContextPath();
		String carId = req.getParameter("carId");
		String brandId = req.getParameter("brandId");
		
		
		dto.setBodyId(req.getParameter("bodyList2"));
		dto.setDetailName(req.getParameter("upDetailName"));
		dto.setDetailPrice(Integer.parseInt(req.getParameter("upDetailPrice")));
		dto.setDetailId(req.getParameter("detailId"));
		dao.updateDetail(dto);
		
		resp.sendRedirect(cp+"/detail/list.do?brandId="+brandId+"&carId="+carId);

	}
	
	protected void delDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DetailDAO dao = new DetailDAO();
		String cp = req.getContextPath();
		String carId = req.getParameter("carId");
		String brandId = req.getParameter("brandId");
		
		dao.deleteDetail(req.getParameter("detailId"));
		
		resp.sendRedirect(cp+"/detail/list.do?brandId="+brandId+"&carId="+carId);
	}
}
