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
	info();
});

$(function(){
	setInterval("info()", 10000); // 10초
});

function info(){
	var url = "test4_ok.jsp";
	var query = "tmp="+new Date().getTime();
	
	$.ajax({
		type:"get"
		,url:url
		,data:query
		,dataType:"json"
		,success:function(data){
			var num = data.num;
			var time= data.time;
			
			$("#snum").html(num);
			$("#stime").html(time);
		}
		,error:function(e){
			console.log(e.responseText);
		}
	});
}

</script>
</head>
<body>
<div id="resultLayout">
	<p>번호:<span id="snum">0</span></p>
	<p>시간:<span id="stime">00:00:00</span></p>
</div>
</body>
</html>