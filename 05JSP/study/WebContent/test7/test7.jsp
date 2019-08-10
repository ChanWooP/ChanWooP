<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<%
	String cp = request.getContextPath();

	String pageNum = request.getParameter("page");
	int current_page = 1;
	if(pageNum!=null)
		current_page = Integer.parseInt(pageNum);
	
	int total_page = 370;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<tf:paginate total_page="<%=total_page %>" uri="test7.jsp" current_page="<%=current_page %>"/>
</div>
<p>
	<tf:select name="city" seoul="서울" busan="부산" Incheon="인천"></tf:select>
</p>

<p>
	<tf:sum end="10" begin="1">
		결과 : ${sum}<br>
	</tf:sum>
</p>

<p>
	<tf:sum2 end="10" var="s" begin="1"></tf:sum2>
	결과 : ${s}<br>
</p>
</body>
</html>
