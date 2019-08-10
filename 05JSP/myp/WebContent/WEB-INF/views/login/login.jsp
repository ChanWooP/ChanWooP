<%@ page contentType="text/html; charset=UTF-8"%>
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
<title>CIS</title>
<link rel="shortcut icon" href="<%=cp%>/resource/img/favicon.ico">
<link rel="stylesheet" href="<%=cp%>/resource/css/style.css" type="text/css">
<link rel="stylesheet" href="<%=cp%>/resource/css/login.css" type="text/css">
</head>
<body>
<div class="container">
	<div class="login">
		<div class="logo">
			<img src="<%=cp%>/resource/img/logoo.png">
		</div>	
		<div class="login_main">
			<form name="loginForm" method="post">
				<label for="userId">아이디</label>
				<input type="text" class="compTF" name="userId" maxlength="20">
				<label for="userPw">비밀번호</label>
				<input type="password" class="compTF" name="userPw" maxlength="20">
				<button type="button" class="compbtn" onclick="login()">로그인</button>
				<p class="loginp" onclick="opens()">회원가입</p>
			</form>
		</div>
		<p>${msg}</p>
	</div>
</div>
<div id="addModal" class="modal">
	<div class="modal-content">
		<form name="addForm" method="post">
			<span class="close" onclick="closes()">&times;</span>
			<h2>회원가입</h2>
			<label for="adduserId">아이디</label>
			<input type="text" class="compTF" name="adduserId" maxlength="20">
			<br>
			<label for="adduserPw">비밀번호</label>
			<input type="password" class="compTF" name="adduserPw" maxlength="20">
			<br>
			<label for="adduserPwChk">비밀번호 확인</label>
			<input type="password" class="compTF" name="adduserPwChk" maxlength="20">
			<br>
			<label for="addName">별명</label>
			<input type="text" class="compTF" name="addName" maxlength="20">
			<button type="button" class="compbtn" onclick="addUser()">가입하기</button>
		</form>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function login(){
	var f = document.loginForm;
	f.action = "<%=cp%>/user/login_ok.do";
	f.submit();
}

function closes(){
	$("#addModal").hide();
}s

function opens(){
	$("#addModal").show();
}

function addUser(){
	var f = document.addForm;
	
	var id = f.adduserId.value;
	var pw = f.adduserPw.value;
	var pwc = f.adduserPwChk.value;
	
	if(id == ""){
		alert("아이디를 입력하세요");
		return;
	}
	if(pw != pwc){
		alert("비밀번호를 확인해주세요");
		return;
	}
	
	f.action = "<%=cp%>/user/addUser.do";
	f.submit();
	
}
</script>
</body>
</html>