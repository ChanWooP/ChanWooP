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
</head>
<body>
<div>
	<form name="mForm" method="post">
	<table style="border-collapse: collapse; border-spacing: 0;">
		<tr>
			<td colspan=${15+1+(15/5) } style="text-align:center; background-color:gray;">
				스크린s
			</td>
		</tr>
		<c:forEach var="rows" begin="1" end="10">
		<tr>
			<c:forEach var="cols" begin="1" end="15">
				<c:forEach var="li" items="${list}">
					<c:if test="${li ==  'A'+rows+cols}">
						<td><input type="checkbox" class="cbox" name="list" value="A${rows}${cols}" checked="checked" disabled="disabled"></td>
					</c:if>
				</c:forEach>
				<c:forEach var="li" items="${list}">
					<c:if test="${li !=  'A'+rows+cols}">
						<td><input type="checkbox" class="cbox" name="list" value="${rows}${cols}"></td>
					</c:if>
				</c:forEach>
				<c:if test="${cols%5==0 && cols<15}">
					<td style="background-color:gray;">&nbsp;</td>
				</c:if>
			</c:forEach>
		</tr>
		</c:forEach>
	</table>
	</form>
	<button type="button" id="submit">예매하기sss</button>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>


$(".cbox").click(function(){
	var count = 0;
	$(".cbox").each(function(){
		var checked = $(this).prop('checked');
		if(checked){
			count++;
		}	
	});
	if(count == 5){
		alert("5명이상은 예매가 불가능합니다s.");
		$(this).prop('checked',false);
	}
});

$('#submit').click(function(){
	var f = document.mForm;
	f.action = "<%=cp%>/movie/submit";
	f.submit();
});
</script>
</body>
</html>