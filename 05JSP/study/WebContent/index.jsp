<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String cp = request.getContextPath();
	Calendar cal = Calendar.getInstance();
	String s = String.format("%1$tF %1$tA %1$tT",cal);
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<p>반가워요</p>
지금은<%= s %> 입니다
<div>
<a href="<%=cp %>/bbs/list.do">게시판</a>
</div>
</body>
</html>