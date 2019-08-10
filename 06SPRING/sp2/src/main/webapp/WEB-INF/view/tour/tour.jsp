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

select{
	width:30%;
	height:50%;
	font-size:20px;
	text-align:center;
	font-weight:bold;
}

#btnOk{
	width:30%;
	height:50%;
	font-size:20px;
	font-weight:bold;
}

#btnBack{
	width:92%;
	height:50%;
	font-size:20px;
	font-weight:bold;
}


</style>
</head>
<body>
<div>
<select id="Tsido">
<option value="">::시도선택::</option>
<c:forEach var="sidos" items="${sidoList}">
<option value="${sidos.snum }">${sidos.sido }</option>
</c:forEach>
</select>

<select id="Tcity">
<option value="">::도시선택::</option>
</select>

<button type="button" id="btnOk">확인</button>
<p><button type="button" id="btnBack">돌아가기</button></p>
</div>

<script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery-1.12.4.min.js"></script>
<script>
$(function(){
	$("#Tsido").change(function(){
		var snum = $(this).val();
		var url = "<%=cp%>/tour/cityList";
		var query = "num="+snum;

		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data){
				var out = "<option value=''>::도시선택::</option>";
				$.each(data.list, function(index, item){
					var snum = item.snum;
					var cnum = item.cnum;
					var city = item.city;
					
					out += "<option value="+cnum+">"+city+"</option>";
				});
				$("#Tcity").empty();
				$("#Tcity").append(out);
			}
			,error:function(e){
				console.log(e.responseText);
			}
		});
	});
	
	$("#btnOk").click(function(){
		var sido = $("#Tsido option:checked").text();
		var city = $("#Tcity option:checked").text();
		var snum = $("#Tsido option:checked").val();
		var cnum = $("#Tcity option:checked").val();
		alert(sido+":"+snum+", "+city+":"+cnum);
	});
	
	$("#btnBack").click(function(){
		location.href = "<%=cp%>/tour/main";
	});
});
</script>
</body>
</html>