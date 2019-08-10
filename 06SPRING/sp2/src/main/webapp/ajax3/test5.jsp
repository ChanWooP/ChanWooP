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
	var query = "tmp="+new Date().getTime();
	var url = "test4_ok.jsp?"+query;
	
	$.getJSON(url, function(data){
		$("#snum").html(data.num);
		$("#stime").html(data.time);
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