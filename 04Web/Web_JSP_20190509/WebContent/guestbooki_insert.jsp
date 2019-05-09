<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*, com.connection.*, com.guestbook.*"%>
<%!%>
<%
	GuestbookDAO agdao = new GuestbookDAO();
	request.setCharacterEncoding("UTF-8");

	String name_ = request.getParameter("name_");
	String pw = request.getParameter("pw");
	String contents = request.getParameter("contents");
	String ipaddress = request.getRemoteAddr();

	int result = 0;

	result = agdao.addguestbook(new Guestbook(name_, ipaddress, contents, pw));

	//강제 페이지 전환
	response.sendRedirect("guestbook.jsp?result=" + result);
%>