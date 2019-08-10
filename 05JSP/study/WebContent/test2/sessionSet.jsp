<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();

	//세션 유지시간(단위 : 초)
	session.setMaxInactiveInterval(60*20);
	
	//세션에 정보 저장
	session.setAttribute("name", "홍길동");
	session.setAttribute("id", "test");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>세션 설정</h3>
	
	<a href="test.jsp">돌아가기</a>
	
</body>
</html>