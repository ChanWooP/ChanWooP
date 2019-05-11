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
	
	int scount_ = Integer.parseInt(sc);

	SubjectinfoDAO sidao =  new SubjectinfoDAO();
	
	int addsublist = sidao.addsubjectList(new Subjectinfo(subjectid_, sname_, sdate_, scount_));

	String result = "";
	
	if (addsublist == 0) {
		result = "fail";
	} else {
		result = "success";
	}

	
	//강제 페이지 전환
	response.sendRedirect("submanage.jsp?result=" + result);

%>
