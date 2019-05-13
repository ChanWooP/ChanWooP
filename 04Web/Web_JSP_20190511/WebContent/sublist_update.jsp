<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import = "com.exam.*"%>
<%!

%>
<%
	String path = request.getContextPath();
	request.setCharacterEncoding("UTF-8");
	StringBuilder sb = new StringBuilder();
	
	String subjectid_ = request.getParameter("subjectid_");
	String sname_ = request.getParameter("sname_");
	String sdate_ = request.getParameter("sdate_");
	String sc = request.getParameter("scount_");
	String sid = request.getParameter("sid");
	
	int scount_ = Integer.parseInt(sc);

	SubjectinfoDAO sidao =  new SubjectinfoDAO();
	
	int upsublist = sidao.upSubjectList(subjectid_, sname_, sdate_, scount_, sid);

	String result = "";
	
	if (upsublist == 0) {
		result = "fail";
	} else {
		result = "success";
	}

	
	//강제 페이지 전환
	response.sendRedirect("submanage.jsp?result2=" + result);
%>