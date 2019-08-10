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
<form enctype="multipart/form-data">
	<table border="1">
		<tbody id="tb">
			<tr height="35">
				<td width="100" align="center">제목</td>
				<td style="padding-left: 10px;"><input type="text" name="subject"></td>
			</tr>
			<tr height="35">
				<td width="100" align="center">파일</td>
				<td style="padding-left: 10px;"><input type="file" name="upload"></td>
			</tr>
		</tbody>
		<tfoot>
			<tr height="35">
				<td colspan="2" align="center">
					<button type="button">등록</button>
				</td>
			</tr>			
		</tfoot>
	</table>
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(function(){
	//$("input[name=upload]").change(function(){ -- 정적 객체만 적용됨
	/*$("body").on("change", "input[name=upload]", function(){ -- 동적 객체도 적용됨
		if(!$(this).val()) return false;
		
		var $tr, $td, $input;
		$tr = $("<tr>", {height:"35"});
		$td = $("<td>", {width:"100", style:"text-align:center;", html:"파일"});
		$tr.append($td);
		
		$td = $("<td>", {style:"padding-left:10px;"});
		$input = $("<input>", {type:"file", name:"upload"});
		$td.append($input);
		$tr.append($td);
		
		$("#tb").append($tr);
		
	});*/
	$("input[name=upload]").change(function(){
		if(! $(this).val()) return false;
		
		//clone() 클론복제 , clone(true) 이벤트 까지 같이 복사
		var $tr = $(this).closest("tr").clone(true);
		$tr.find("input").val("");
		$("#tb").append($tr);
	});
});
</script>
</body>
</html>