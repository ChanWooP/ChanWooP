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
<style type="text/css">
* {
	margin: 0px; padding: 0px;
}

body {
	font-size: 14px;
	font-family: 맑은 고딕, 돋움;
}
a{
	color: #000000;
	text-decoration: none;
	cursor: pointer;
}
a:active, a:hover {
	text-decoration: underline;
	color: tomato;
}
.btn {
    color:#333333;
    font-weight:500;
    font-family:"Malgun Gothic", "맑은 고딕", NanumGothic, 나눔고딕, 돋움, sans-serif;
    border:1px solid #cccccc;
    background-color:#fff;
    text-align:center;
    cursor:cursor;
    padding:3px 10px 5px;
    border-radius:4px;
}
.btn:active, .btn:focus, .btn:hover {
    background-color:#e6e6e6;
    border-color: #adadad;
    color: #333333;
}
.boxTF {
    border:1px solid #999999;
    padding:3px 5px 5px;
    border-radius:4px;
    background-color:#ffffff;
    font-family:"Malgun Gothic", "맑은 고딕", NanumGothic, 나눔고딕, 돋움, sans-serif;
}

nav, section {
	width: 800px;
	margin: 0px auto;
}

section article {
	margin-top: 30px;
}

nav {
	clear: both;
}
nav ul{
	padding-top:10px;
	list-style: none;
}
nav ul li {
	/*display: inline-block;*/
	float:left;
	border-left:1px solid #999; /* 메뉴 왼쪽에 "|" 표시 */
	padding:0 10px;
}
nav ul li:first-child{ /* 메뉴 제일 왼쪽의 "|"는 삭제  */
	border-left:none;
} 
</style>
</head>
<body>

<nav>
	<ul>
		<li><a href="<%=cp%>/main">Main</a></li>
		<li><a href="<%=cp%>/guest">방명록</a></li>
		<li><a href="#">게시판</a></li>
		<li><a href="#">공지사항</a></li>

<c:if test="${empty sessionScope.loginMember.userId}">
		<li style="float: right;"><a href="#">회원가입</a></li>
		<li style="float: right; border-left:none;"><a href="<%=cp%>/login">로그인</a></li>
</c:if>		
<c:if test="${not empty sessionScope.loginMember.userId}">
		<li style="float: right;"><a href="<%=cp%>/logout">로그아웃</a></li>
		<li style="float: right; border-left:none;"><span>${sessionScope.loginMember.userName}</span>님</li>
</c:if>		
	</ul>
</nav>

<section>
	<article style="width: 400px; margin: 200px auto;">
		<form action="<%=cp%>/login" method="post">
			<div style="margin-bottom: 30px; font-size: 45px; font-weight: 700; text-align: center;">
				로 그 인
			</div>
			<div style="margin-bottom: 10px;">
				<input type="text" name="userId" placeholder="아이디"
				          class="boxTF"
				          style="width: 100%; height: 50px; font-size: 16px; padding-left: 10px; box-sizing: border-box;">
			</div>
			<div style="margin-bottom: 10px;">
				<input type="password" name="userPwd" placeholder="패스워드"
				           class="boxTF"
				           style="width: 100%; height: 50px; font-size: 16px; padding-left: 10px; box-sizing: border-box;">
			</div>
			<div>
				<button type="submit" class="btn"
				           style="width: 100%; height: 50px; font-size: 16px; box-sizing: border-box;">로그인</button>
			</div>
		</form>
	</article>
</section>

</body>
</html>