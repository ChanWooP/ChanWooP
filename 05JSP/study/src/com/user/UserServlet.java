package com.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		ServletConfig config = getServletConfig();
		ServletContext context = getServletContext();
		
		String subject = config.getInitParameter("subject");
		String year = config.getInitParameter("year");
		
		String city = context.getInitParameter("city");
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("<html><body>");
		out.print("<h3>ServletConfig/ServletContext ¿¹Á¦</h3>");
		out.println("<p>subject:"+subject+"</p>");
		out.println("<p>year:"+year+"</p>");
		out.println("<p>city:"+city+"</p>");
		out.print("</body></html>");
	}
}
