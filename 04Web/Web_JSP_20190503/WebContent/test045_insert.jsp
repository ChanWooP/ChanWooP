<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*, com.connection.*"%>
<%
	request.setCharacterEncoding("utf-8");
	String mid_ = request.getParameter("mid_");
	String name_ = request.getParameter("name_");
	String phone = request.getParameter("phone");
	String email = request.getParameter("email");
	String result = "faile";
	Connection conn = null;
	PreparedStatement stmt = null;
	int count = 0;

	try {
		conn = MySQLConnection80.connect();

		String sql = "INSERT INTO Members (mid_, name_, phone, email)\r\n" + "VALUES(?, ?, ?, ?)";
		stmt = conn.prepareStatement(sql);

		stmt.setString(1, mid_);
		stmt.setString(2, name_);
		stmt.setString(3, phone);
		stmt.setString(4, email);

		stmt.executeUpdate();
		result = "success";
	} catch (Exception e) {
		System.out.println(e);
	} finally {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se) {
		}
		try {
			MySQLConnection80.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	response.sendRedirect("test045.jsp?result="+result);
%>