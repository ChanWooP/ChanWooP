<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!-- id는 변수이름 class는 생성할 클래스 -->
<jsp:useBean id="ob" class="com.calc.Calc"/>
<!-- set함수를 호출하여 받은 값으로 초기화시키기(name값과 변수값이 동일해야함) -->
<jsp:setProperty property="*" name="ob"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=ob.result() %>	
</body>
</html>
