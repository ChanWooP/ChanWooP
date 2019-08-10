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
<title>Insert title here</title>
</head>
<body>
<c:set var="url" value="https://www.naver.com"/>
<c:import url="${url}" var="naver"/>

<h3>naver</h3>
<c:out value="${naver}" escapeXml="false"/>

<h3>naver</h3>
<c:out value="${naver}"/>
</body>
</html>