<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*, com.connection.*, com.subject.*"%>
<%
	SubjectDAO sDAO = new SubjectDAO();
	request.setCharacterEncoding("UTF-8");
	
	String subId = request.getParameter("subId");
	String subName = request.getParameter("subName");
	String subDate_ = request.getParameter("subDate_");
	String subNum = request.getParameter("subNum");
	
	int result = 0;
	
	result = sDAO.addSubject_(new Subject_(subId, subName, subDate_, subNum));

	response.sendRedirect("Subject.jsp?resultI=" + result);
%>