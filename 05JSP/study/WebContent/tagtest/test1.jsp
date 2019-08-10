<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="/WEB-INF/mytag.tld" %>
<%
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
합 <my:sum num="100"/>
<hr>
<my:hello>
자바
</my:hello>

<my:forEach var="a" begin="1" end="10" step="1">
	${a } 번째 <br>
</my:forEach>
</body>
</html>