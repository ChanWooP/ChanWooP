<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import = "java.util.*" %>
<%
	String cp = request.getContextPath();

	List<String> list = new ArrayList<>();
	list.add("서울");
	list.add("대구");
	list.add("대전");
	list.add("부산");
	list.add("울산");
	
	request.setAttribute("list", list);
%>
<jsp:forward page="test7_ok.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>choose</title>
</head>
<body>

</body>
</html>