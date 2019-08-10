<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div style="width: 300px; margin: 30px auto;">
	<h3>꿈의 로또</h3>

	<c:forEach var="a" items="${lotto}" varStatus="status">
		<p> ${status.count} 번째 : 
		  <c:forEach var="b" items="${a}">
		      <span style="margin-right: 5px;">${b}</span>
		  </c:forEach>
		</p>
	</c:forEach>
</div>

</body>
</html>