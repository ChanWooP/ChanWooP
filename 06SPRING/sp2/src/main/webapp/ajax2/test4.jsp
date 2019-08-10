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
		var query = $("form[name=myForm]").serialize();
		var url = "test4_ok.jsp?"+query;
		
		// jquery AJAX
		$.ajax({
			type:"POST"
			,url:url
			,data:query
			,success:function(data){
				$("#resultLayout").html(data);
			}
			,beforeSend:check // 필요할떄만 쓰면 됨
			,error:function(e){ // 필요할떄만 쓰면 됨
				console.log(e.responseText);
			}
		});
	});
	
	function check(){
		if(! $("#num1").val()){
			$("#num1").focus();
			return false;
		}
		
		if(! $("#num2").val()){
			$("#num2").focus();
			return false;
		}
		
		
		
		return true;
	}
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