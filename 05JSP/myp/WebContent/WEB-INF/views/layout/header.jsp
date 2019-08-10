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
<title>Insert title here</title>
<link rel="stylesheet" href="<%=cp%>/resource/css/layout/header.css" type="text/css">
</head>
<body>
<div class="header-top">
	<div class="header-left">
		<a href="<%=cp%>/main/main.do">
			<img src="<%=cp %>/resource/img/logoo.png">
		</a>
	</div>
	<div class="header-right">
		<a style="color:red;">${sessionScope.user.userName}</a>님 로그인 하신 것을 환영합니다
		&nbsp;|&nbsp;
		<a href="<%=cp%>/user/logout.do">로그아웃</a>
	</div>
</div>
<div class="menu">
	<ul class="nav">
		<li>
			<a href="#">차량가이드</a>
			<ul>
				<li><a href="<%=cp%>/brand/list.do">브랜드별 차량정보</a></li>
			</ul>
		</li>
		<li>
			<a href="#">리뷰</a>
			<ul>
				<li><a href="<%=cp%>/bbs/list.do?page=1">시승기</a></li>
			</ul>
		</li>
	</ul>
</div>
</body>
</html>