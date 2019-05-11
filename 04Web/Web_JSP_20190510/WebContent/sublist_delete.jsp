<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import = "com.exam.*"%>
<%!

%>
<%
	String path = request.getContextPath();
	request.setCharacterEncoding("UTF-8");
	StringBuilder sb = new StringBuilder();
	
	String sid = request.getParameter("sid");
	String subid = request.getParameter("subid");
	String sc = request.getParameter("scount");
	
	
	int scount = Integer.parseInt(sc);

	SubjectinfoDAO sidao =  new SubjectinfoDAO();
	
	int delsublist = sidao.delsubjectList(sid, subid, scount);

	String result = "";
	
	if (delsublist == 0) {
		result = "fail";
	} else {
		result = "success";
	}

	
	//강제 페이지 전환
	response.sendRedirect("submanage.jsp?result1=" + result); 
%>