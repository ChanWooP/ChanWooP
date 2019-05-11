<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*, com.connection.*, com.subject.*"%>
<%
	SubjectDAO sDAO = new SubjectDAO();
	request.setCharacterEncoding("UTF-8");
	
	String subId = request.getParameter("SubId_Delete");
	
	int result = 0;
	
	result = sDAO.delSubject_(subId);

	response.sendRedirect("Subject.jsp?resultD=" + result);
%>