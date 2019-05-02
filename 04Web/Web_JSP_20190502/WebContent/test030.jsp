<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%!
private int method(int a, int b){
	return a+b;
}
%>
<%
	StringBuilder sb = new StringBuilder();

	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH + 1);
	int date = cal.get(Calendar.DATE);
	sb.append(String.format("%d %d %d<br>",year, month, date));

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
	오늘날짜 : <%=sb.toString()%>
	</div>
</body>
</html>
