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
<title>choose</title>
</head>
<body>
	<form method="post">
		<p>수 : <input type="text" name="num"></p>
		<p><button type="submit">확인</button>
	</form>
	
	<c:if test="${not empty param.num}">
		<c:choose>
			<c:when test="${param.num%3==0&&pram.num%4==0}">
				${param.num} : 3과 4의 배수
			</c:when>
			<c:when test="${param.num%3==0&&pram.num%4==0}">
				${param.num} : 3의 배수
			</c:when>
			<c:when test="${param.num%4==0&&pram.num%4==0}">
				${param.num} : 4의 배수
			</c:when>
			<c:otherwise>
				${param.num} : 3또는 4의 배수가 아님
			</c:otherwise>
		</c:choose>
	</c:if>
</body>
</html>