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
<style>
div{
	width:510px;
	height:130px;
	margin: 30px auto 0px;
}
p{
	width:100%;
	height:50%;
	border: 1px solid black;
	text-align:center;
	font-size:35px;
	font-weight:bold;
	cursor:pointer;
}
</style>
</head>
<body>
<div>
<p id="tour">도시선택</p>
<p id="manager">도시관리</p>
</div>
<script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery-1.12.4.min.js"></script>
<script>
$(function(){
	$("#tour").click(function(){
		location.href = "<%=cp%>/tour/tour";
	});
	
	$("#manager").click(function(){
		location.href = "<%=cp%>/tour/manage";
	});
});
</script>
</body>
</html>