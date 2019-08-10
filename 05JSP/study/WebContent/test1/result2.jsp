<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>이름 : ${vo.name}</p>
<p>이름 : ${param.name}</p>
<p>과목 : ${param.subject}</p>
<p>점수 : ${param.score}</p>
<p>판정 : ${pan}</p>
</body>
</html>