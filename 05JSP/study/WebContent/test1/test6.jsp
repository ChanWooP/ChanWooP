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
	<table border="1">
		<c:forEach var="n" begin="1" end="5">
			<tr>
				<c:forEach var="m" begin="1" end="7">
					<td width="50">${n*m}</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
		
	</c:if>

</body>
</html>