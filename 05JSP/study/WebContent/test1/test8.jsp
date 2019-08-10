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
		<c:set var="s" value="0"/>
		<c:forEach var="n" begin="1" end="${param.num}">
			<c:set var="s" value="${s+n}"/>
		</c:forEach>
		<p>1~${param.num} 까지 합 : ${s}</p>`
	</c:if>
</body>
</html>