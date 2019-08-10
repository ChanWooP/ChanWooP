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
<h3>쿠키생성</h3>
<p><a href="main.jsp">돌아가기</a></p>
<%
	Cookie[] cc = request.getCookies();
	if(cc!=null){
		for(Cookie c:cc){
			String name = c.getName();
			String value = c.getValue();
			if(name.equals("city")){
				value = URLDecoder.decode(value, "utf-8");
			}
			
			out.print("<p>"+name+":"+value+"</p>");
		}
	}
%>

</body>
</html>