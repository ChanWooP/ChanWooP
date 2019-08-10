<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("utf-8");
	String cp = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>study</title>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

body {
	font-size: 14px;
	font-family: "Malgun Gothic", "맑은 고딕", NanumGothic, 나눔고딕, 돋움, sans-serif;
}

a {
	color: #000000;
	text-decoration: none;
	cursor: pointer;
}

a:active, a:hover {
	text-decoration: underline;
	color: tomato;
}

textarea:focus, input:focus {
	outline: none;
}

.btn {
	color: #333333;
	font-weight: 500;
	font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
	border: 1px solid #cccccc;
	background-color: #ffffff;
	text-align: center;
	cursor: pointer;
	padding: 3px 10px 5px;
	border-radius: 4px;
}

.btn:active, .btn:focus, .btn:hover {
	background-color: #e6e6e6;
	border-color: #adadad;
	color: #333333;
}

.boxTF {
	border: 1px solid #999999;
	padding: 3px 5px 5px;
	border-radius: 4px;
	background-color: #ffffff;
	font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
}

.selectField {
	border: 1px solid #999999;
	padding: 2px 5px 4px;
	border-radius: 4px;
	font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
}

.title {
	font-weight: bold;
	font-size: 16px;
	font-family: 나눔고딕, "맑은 고딕", 돋움, sans-serif;
}
</style>

<script type="text/javascript">
	function deleteScore(hak, page) {
		if (confirm("삭제 하시겠습니까 ?")) {
			var url = "<%=cp%>/score/delete.do?hak="+hak+"&page="+page;
			location.href=url;
		}
	}

	function updateScore(hak, page) {
		var url = "<%=cp%>/score/update.do?hak="+hak+"&page="+page;
		location.href=url;
	}
</script>

</head>
<body>

	<div style="margin: 0px auto; margin-top: 30px;">
		<table style="width: 700px; margin: 0px auto; border-spacing: 0px;">
			<tr height="35">
				<td align="left" class="title">| 성적처리</td>
			</tr>
		</table>
		
		<table
			style="width: 700px; margin: 10px auto 0px; border-spacing: 0px;">
			
			<tr height="35">
				<td align="left">&nbsp;</td>
				<td>국어평균 : ${list2.get(0)} / 영어평균 : ${list2.get(1)} / 수학평균 : ${list2.get(2)}</td>
				<td align="right"><input type="button" value="  등록하기  "
					class="btn"
					onclick="javascript:location.href='<%=cp%>/score/insert.do';">
				</td>
			</tr>
		</table>
		<table
			style="width: 700px; margin: 0px auto; border-spacing: 1px; background: #cccccc;">
			<tr height="30" bgcolor="#eeeeee" align="center">
				<th width="60">학번</th>
				<th width="80">이름</th>
				<th width="80">생년월일</th>
				<th width="60">국어</th>
				<th width="60">영어</th>
				<th width="60">수학</th>
				<th width="60">총점</th>
				<th width="60">평균</th>
				<th width="60">석차</th>
				<th>수정</th>
			</tr>
			<c:forEach var="dto" items="${list}">
				<tr height="35" bgcolor="#ffffff" align="center">
					<td>${dto.hak}</td>
					<td>${dto.name }</td>
					<td>${dto.birth }</td>
					<td>${dto.kor }</td>
					<td>${dto.eng }</td>
					<td>${dto.mat }</td>
					<td>${dto.tot }</td>
					<td>${dto.ave }</td>
					<td>${dto.rank }</td>
					<td><input type="button" value="수정" onclick="updateScore('${dto.hak}','${page}')" class="btn"> 
					<input type="button" value="삭제"onclick="deleteScore('${dto.hak}','${page}')" class="btn"></td>
				</tr>
			</c:forEach>
		</table>
		
		<table style="width:700px; margin:0px auto;">
			<tr height="30" align="center">
				<td>${dataCount==0?"등록된 자료가 없습니다":paging}</td>
			</tr>
		</table>
	</div>

</body>
</html>