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
<h3>회원가입 : 2단계</h3>
<form action="<%=cp%>/member/step2" method="post">
	<p>아이디 : <input type="text" name="id"></p>
	<p>패스워드 : <input type="password" name="pwd"></p>
	<p>전화번호 : <input type="text" name="tel"></p>
	<p>
		<button type="button" onclick="javascript:location.href='<%=cp%>/member/form';">이전단계</button>
		<button type="submit">회원가입</button>
	</p>
	<p>${message }</p>
</form>
</body>
</html>