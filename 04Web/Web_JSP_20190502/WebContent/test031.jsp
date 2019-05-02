<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.test.*" %>
<%
	StringBuilder sb = new StringBuilder();
	int ten = 12;
	String two = Test.decToBin(ten);
	sb.append(String.format("%d -> %s",ten, two));
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
	<%= sb.toString() %>
	</div>
</body>
</html>