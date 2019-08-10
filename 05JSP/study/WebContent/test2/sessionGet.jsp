<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();

	//세션에 저장된 속성 가져오기
	//String name = (String)session.getAttribute("name");
	
	//세션유지시간
	int interval = session.getMaxInactiveInterval();
	
	//세션아이디(세션마다 고유함)
	String sessionId = session.getId();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>세션 확인</h3>
	<div>
		<p>세션아이디 : <%=sessionId%></p>
		<p>세션유지시간 : <%=interval%></p>
		<p>이름 : ${sessionScope.name}</p>
		<p>유저아이디 : ${sessionScope.id}</p>
		<p>sessionScope는 EL에서 session을 나타내는 객체</p>
	</div>
	<a href="test.jsp">돌아가기</a>
</body>
</html>