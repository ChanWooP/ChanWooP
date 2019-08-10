<%@page import="com.util.MyUtil"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();

	MyUtil util = new MyUtil();
	
	//JSP 에서 page 는 예약어
	String pageNum = request.getParameter("page");
	int current_page = 1;
	if(pageNum != null)
		current_page = Integer.parseInt(pageNum);
	
	int dataCount = 975;
	int total_page;
	int rows = 10;
	
	total_page = util.pageCount(rows, dataCount);
	if(current_page > total_page)
		current_page = total_page;
	
	String listUrl = "test.jsp";
	
	String paging = util.paging2(current_page, total_page, listUrl);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>페이징 테스트</h3>
<div>
현제페이지 : <%=current_page %> / 전체페이지 : <%=total_page %>
</div>
<div>
<%=paging %>
</div>
</body>
</html>