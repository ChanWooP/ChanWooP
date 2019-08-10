<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();

	Cookie c1 = new Cookie("subject", null);
	c1.setMaxAge(0);
	response.addCookie(c1);
	
	Cookie c2 = new Cookie("city", null);
	c2.setMaxAge(0);
	response.addCookie(c2);
	
	Cookie c3 = new Cookie("tel", null);
	c3.setPath("/");
	c3.setMaxAge(0);
	response.addCookie(c3);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>쿠키삭제</h3>
<p><a href="main.jsp">돌아가기</a></p>
</body>
</html>