<%@page import="java.net.URLDecoder"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*, java.io.*"%>

<%
	String cp = request.getContextPath();
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String contentType = request.getContentType();
	
	Enumeration<String> e = request.getHeaderNames();
	
	while(e.hasMoreElements()){
		String key = e.nextElement();
		String value = request.getHeader(key);
		out.print("<div>"+key+":"+value+"</div>");
	}
	out.println("<hr>");
	
	String queryString = request.getQueryString();
	out.println("get 방식...<br>");
	out.println(queryString+"<br>");
	out.println("<hr>");
	
	out.println("<h3>[[request로 넘어온 데이터]]</h3>");
	InputStream is = request.getInputStream();
	byte[] b = new byte[1024];
	int size;
	String str;
	
	//enctype="multipart/form-data" 인 경우는 받을 수 없다
	out.println(request.getParameter("subject")+"<br>");
	
	out.println("post 방식...<br>");
	while((size=is.read(b))!=-1){
		// method="post" enctype="application/x-www.form.urlencoded" 인 경우 
		//str = new String(b, 0, size);
		//str = URLDecoder.decode(str,"UTF-8");
		
		// method="post" enctype="multipart/form-data" 인 경우 
		str = new String(b, 0, size, "utf-8");
		
		out.println("<div>"+str+"</div>");
	}
%>
</body>
</html>