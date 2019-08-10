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
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(function(){
	$("#btnSend").click(function(){
		//var query = "num1="+$("#num1").val()+"&num2="+$("#num2").val()+"&oper="+encodeURIComponent($("#oper").val());
		//var query = $("form[name=myForm]").serialize();
		var url = "test3_ok.jsp?";
		
		var n1 = $("#num1").val();
		var n2 = $("#num2").val();
		var oper = encodeURIComponent($("#oper").val());
		
		
		// jquery AJAX POST
		$.post(url, {num1:n1, num2:n2, oper:oper}, function(data){
			$("#resultLayout").html(data);
		});
		
	});
});
</script>
</head>
<body>
<form name="myForm">
	<input type="text" name="num1" id="num1">
	<select name="oper" id="oper">
		<option value="+">더하기</option>
		<option value="-">빼기</option>
		<option value="*">곱하기</option>
		<option value="/">나누기</option>
	</select>
	<input type="text" name="num2" id="num2">
	<button type="button" id="btnSend">연산</button>
</form>
<hr>
<div id="resultLayout"></div>
</body>
</html>