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
		<h1>인원추가</h1>
		<hr>
	</div>
		<form name="personForm" method="post" enctype="multipart/form-data">
			<p>이름 : <input type="text" name="personName"></p>
			<p>핸드폰 : <input type="text" name="personTel"></p>
			<p>이메일 : <input type="text" name="personEmail"></p>
			<p>우편번호 : <input type="text" name="personPost"></p>
			<p>주소1 : <input type="text" name="personAddr1"></p>
			<p>주소2 : <input type="text" name="personAddr2"></p>
			<p>사진 : <input type="text" name="personPhoto" value="1"></p>
			<button type="button" onclick="insert();">작성</button>
		</form>
	<div class="content-container">
		
	</div>
	<div class="footer-container">

	</div>
</div>

<script type="text/javascript">
	function insert(){
		var f = document.personForm;
		
		f.action = "<%=cp%>/person/article";
		f.submit();
	}
</script>
</body>
</html>