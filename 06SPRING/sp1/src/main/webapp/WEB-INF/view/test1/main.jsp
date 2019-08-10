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
<p><a href="<%=cp%>/test1/request">확인1 : 에러</a></p>
<p><a href="<%=cp%>/test1/request?age=20&gender=m">확인2</a></p>
<p><a href="<%=cp%>/test1/request?age=20">확인3</a></p>
<hr>
<p><a href="<%=cp%>/test1/request2">확인1 : 에러(400 - Bad Request)</a></p>
<p><a href="<%=cp%>/test1/request2?age=20&gender=m">확인2</a></p>
<p><a href="<%=cp%>/test1/request2?age=20">확인3 : 에러(400 - Bad Request)</a></p>
<hr>
<p><a href="<%=cp%>/test1/request3">확인1 : 에러(400 - Bad Request)</a></p>
<p><a href="<%=cp%>/test1/request3?age=20&gender=m">확인2</a></p>
<p><a href="<%=cp%>/test1/request3?age=20">확인3</a></p>
<hr>
<p><a href="<%=cp%>/test1/request4">확인1</a></p>
<p><a href="<%=cp%>/test1/request4?age=20&g=m">확인2</a></p>
<p><a href="<%=cp%>/test1/request4?age=20">확인3</a></p>
</body>
</html>