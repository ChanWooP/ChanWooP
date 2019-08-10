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
<title>title</title>
</head>
<body>
	<form method="post">
		<p>수 : <input type="text" name="num"></p>
		<p><button type="submit">확인</button>
	</form>
	
	<c:if test="${not empty param.num}">
		<c:if test="${param.num % 2 == 0 }">${param.num} : 짝수</c:if>
		<c:if test="${param.num % 2 != 0 }">${param.num} : 홀수</c:if>
	</c:if>
	
</body>
</html>