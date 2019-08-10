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
<h3>회원가입 : 1단계</h3>
<form action="<%=cp%>/member/step1" method="post">
	<p>이름 : <input type="text" name="name" value="${member.name }"></p>
	<p>이메일 : <input type="text" name="email" value="${member.email }"></p>
	<p><button type="submit">다음단계</button></p>
	<p>${message }</p>
</form>
</body>
</html>
