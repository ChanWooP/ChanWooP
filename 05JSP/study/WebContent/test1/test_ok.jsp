<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	int score = Integer.parseInt(request.getParameter("score"));
	String grade;
	if(score>=80) grade="우수";
	else if(score>=60) grade="보통";
	else grade="부족";
	
	request.setAttribute("pan", grade);
%>
<jsp:useBean id="vo" class="com.test.TestVO"/>
<jsp:setProperty property="*" name="vo"/>
<%
	request.setAttribute("vo", vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>이곳의 내용은 출력 되지 않는다.</div>
<p>
	forward하면 request, response 객체가 그대로 전달된다.
</p>
<jsp:forward page="result2.jsp">
	<jsp:param value="자바" name="subject"/>
</jsp:forward>
</body>
</html>