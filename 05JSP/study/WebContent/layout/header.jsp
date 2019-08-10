<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int a=10;
%>

<ul>
	<li><a href="main.jsp" class="menuItem ${param.menuItem=='main'?'menuItem-is-disabled':''}">홈</a></li>
	<li><a href="guest.jsp" class="menuItem ${param.menuItem=='guest'?'menuItem-is-disabled':''}">방명록</a></li>
	<li><a href="bbs.jsp" class="menuItem ${param.menuItem=='bbs'?'menuItem-is-disabled':''}">게시판</a></li>
</ul>
    