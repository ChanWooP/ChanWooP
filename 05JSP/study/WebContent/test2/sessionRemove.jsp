<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();

	//세션에 저장된 속성값 제거
	session.removeAttribute("name");
	session.removeAttribute("id");
	
	//세션에 저장된 속성값을 제거하고 세션을 초기화
	session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>세션 제거</h3>
	
	<a href="test.jsp">돌아가기</a>
	
</body>
</html>