<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String name = request.getParameter("name");
	String score = request.getParameter("score");
	String subject = request.getParameter("subject");
	
	String grade = (String)request.getAttribute("pan");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>이름 : <%=name %>	</div>
<div>과목 : <%=subject %>	</div>
<div>점수 : <%=score %>	</div>
<div>결과 : <%=grade %>	</div>
</body>
</html>
