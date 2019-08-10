<%@page import="java.net.URLDecoder"%>
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
</head>
<body>
<p>제품명 | 가격</p>
<p><a href="1.jsp">세탁기 | 100</a></p>
<p><a href="2.jsp">냉장고 | 150</a></p>
<p><a href="3.jsp">노트북 | 150</a></p>
<%
	Cookie[] cc = request.getCookies();
	if(cc!=null){
		out.print("구경한 제품들 : ");
		for(Cookie c:cc){
			String value = c.getValue();
			value = URLDecoder.decode(value, "utf-8");
			
			out.print(value+"&nbsp;");
		}
		
	}
%>
<p><a href="remove.jsp">구경한 제품 초기화</a></p>
</body>
</html>