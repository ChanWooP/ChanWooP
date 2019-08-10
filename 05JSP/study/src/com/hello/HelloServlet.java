package com.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// 서블릿이 메모리에서 파괴될 때 한번 실행
		System.out.println("파괴");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// 서블릿이 메모리에 로딩될 때 한번 실행
		System.out.println("생성");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// 클라이언트가 요청 할 때 마다 실행
		
		try {
			String name = req.getParameter("name");
			int age = Integer.parseInt(req.getParameter("age"));
			
			String result = "성인";
			if(age<19) {
				result = "미성년자";
			}
			
			String msg = name + "님은<b>" + result + "</b> 입니다.";
			
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			out.print("<html>");
			out.print("<body>");
			out.print(msg);
			out.print("</body>");
			out.print("</html>");
			
		}catch(Exception e) {
			getServletContext().log("error");
		}
		
	}
	
}
