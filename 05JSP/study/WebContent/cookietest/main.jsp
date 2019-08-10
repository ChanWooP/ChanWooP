<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p><a href="getCookie.jsp">쿠키가져오기</a></p>
	<p><a href="setCookie.jsp">쿠키설정하기</a></p>
	<p><a href="removeCookie.jsp">쿠키삭제하기</a></p>
</body>
</html>