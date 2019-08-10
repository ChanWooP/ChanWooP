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
		<!-- step은 입력하지 않을 시 기본적으로 1임 -->
		<c:forEach var="n" begin="1" end="9" step="1">
			${param.num} * ${n} = ${param.num * n}<br>
		</c:forEach>
	</c:if>

</body>
</html>