<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=cp %>/resource/css/style.css" type="text/css">
<script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery-1.12.4.min.js"></script>
</head>
<body>

<div class="body-container">
	<div class="header-container">
		<h1>인원확인</h1>
		<hr>
	</div>
	<div class="content-container">
		<table class="board">
			<tr class="theader">
				<th width="5%"><input type="checkbox" name="allCheckPerson"></th>
				<th width="10%">번호</th>
				<th width="10%">이름</th>
				<th width="10%">핸드폰</th>
				<th width="10%">이메일</th>
				<th width="55%">주소</th>
			</tr>
			<tr>
				<th width="5%"><input type="checkbox" name="checkPerson"></th>
				<td width="10%">1</td>
				<td width="10%">홍길동</td>
				<td width="10%">010-1111-1111</td>
				<td width="10%">hong@naver.com</td>
				<td width="55%">(411-222) 경기도 김포시 사우동 조리미로 39-36 원미아파트 103동 110호</td>
			</tr>
		</table>
	</div>
	<div class="footer-container">
		<button class="btnAddPerson">추가</button>
		<button class="btnUpPerson">수정</button>
		<button class="btnDelPerson">삭제</button>
	</div>
</div>

<script type="text/javascript">
// 체크박스 전체 선택 및 해제
$(function(){
	$("input[name=allCheckPerson]").click(function(){
		var chk = $(this).is(":checked");
		
		if(chk){
			$("input[name=checkPerson]").prop("checked",true);
		} else {
			$("input[name=checkPerson]").prop("checked",false);
		}
	});
});

$(function(){
	$(".btnAddPerson").click(function(){
		location.href = "<%=cp%>/person/article";
	});
});

</script>
</body>
</html>