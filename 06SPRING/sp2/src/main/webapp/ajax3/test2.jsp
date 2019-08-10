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
<script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery-1.12.4.min.js"></script>
<script>
	$(function(){
		$("#btnSend").click(function(){
			var q = "num="+$("#num").val();
			var url = "test2_ok.jsp";
			
			$.ajax({
				type:"post"
				,url:url
				,data:q
				,async:false // false:동기, 기본(true:비동기)
				,success:function(data){
					alert("Sd");
					//$("#resultLayout").html("결과:"+data); // 1
				}
				,error:function(e){
					console.log(e.responseText);
				}
			});
			$("#resultLayout").html("AJAX 실행 결과 후 호출됨");// 2
			
		});
	});
</script>
</head>
<body>
<p>
	<input type="text" id="num">
	<button type="button" id="btnSend">결과</button>
</p>
<div id="resultLayout">

</div>
</body>
</html>