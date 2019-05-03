<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*, com.connection.*, java.util.*" %>
<%
	//name 속성(value 값을 가져옴)
	String email = request.getParameter("email");
	System.out.println(email);
	
	// 해당 name 속성을 가진 것들을 모두 가져옴(value 값을 가져옴)
	// checkbox에서 많이 쓰임
	String[] pwd = request.getParameterValues("pwd"); 
	System.out.println(Arrays.toString(pwd));
	
	// 수신받은 전체 데이터를 받는다(value 값을 가져옴)
	Map<String, String[]> map = request.getParameterMap(); 
	System.out.println(Arrays.toString(map.get("rememberme")));
%>
<!DOCTYPE html>
<html>
<head>
<title>JSP</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<style></style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function(){
		
	});
</script>
</head>
<body>
	<div class="container">
		
	</div>
</body>
</html>