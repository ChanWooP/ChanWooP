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
	<form action="<%=cp%>/lotto/request" method="post">
	<p>
	  <input type="text" name="count" required="required" pattern="[1-5]"
	         placeholder="구매개수를 입력하세요...">
	  <button type="submit">구매</button>
	</p>
	</form>
</div>

</body>
</html>