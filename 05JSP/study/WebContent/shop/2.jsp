<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.net.URLEncoder"%>
<%
	String cp = request.getContextPath();

	Cookie c1 = new Cookie("name2",URLEncoder.encode("냉장고","utf-8"));
	response.addCookie(c1);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	품명 : 냉장고
	제조사 : 삼싱
	가격 : 150
	메모 : 싸지만 동작은??
	<p><a href="shop.jsp">돌아가기</a></p>
</body>
</html>