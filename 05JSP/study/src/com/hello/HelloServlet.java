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
		// ������ �޸𸮿��� �ı��� �� �ѹ� ����
		System.out.println("�ı�");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// ������ �޸𸮿� �ε��� �� �ѹ� ����
		System.out.println("����");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// Ŭ���̾�Ʈ�� ��û �� �� ���� ����
		
		try {
			String name = req.getParameter("name");
			int age = Integer.parseInt(req.getParameter("age"));
			
			String result = "����";
			if(age<19) {
				result = "�̼�����";
			}
			
			String msg = name + "����<b>" + result + "</b> �Դϴ�.";
			
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
