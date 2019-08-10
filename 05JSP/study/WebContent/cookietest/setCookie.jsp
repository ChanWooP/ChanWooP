<%@page import="java.net.URLEncoder"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();

	// 쿠키 설정
	Cookie c1 = new Cookie("subject","jsp");
	c1.setMaxAge(60*60); // 쿠키유지시간(단위:초)
	response.addCookie(c1); // 쿠키를 저장(클라이언트 하드디스크에 저장)
	
	// 한글은 반드시 인코딩해야함
	// 쿠키유지시간을 설정하지 않으면 setMaxAge(-1) 로 브라우저를 종료하면 쿠키 제거됨
	Cookie c2 = new Cookie("city",URLEncoder.encode("서울","utf-8"));
	response.addCookie(c2);
	
	// 쿠키는 문자열만 가능
	Cookie c3 = new Cookie("year","2019");
	c3.setMaxAge(0); // 쿠키 생성과 동시에 제거(보통 0을써서 쿠키를 제거함)
	response.addCookie(c3); 
	

	Cookie c4 = new Cookie("tel","010-0000-0000");
	c4.setPath("/"); // 모든 폴더에서 쿠키를 접근할 수 있도록 설정. 기본은 쿠키를 설장한 폴더만 가능
	response.addCookie(c4); 
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>쿠키설정</h3>
<p><a href="main.jsp">돌아가기</a></p>
</body>
</html>