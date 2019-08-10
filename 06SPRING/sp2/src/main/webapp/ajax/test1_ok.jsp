<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
	
	int num1 = Integer.parseInt(request.getParameter("num1"));
	int num2 = Integer.parseInt(request.getParameter("num2"));
	String oper = request.getParameter("oper");
	
	String s = "";
	if(oper.equals("add")){
		s=String.format("%d+%d=%d", num1, num2, num1+num2);
	}else if(oper.equals("sub")){
		s=String.format("%d-%d=%d", num1, num2, num1-num2);
	}else if(oper.equals("mul")){
		s=String.format("%d*%d=%d", num1, num2, num1*num2);
	}else if(oper.equals("div")){
		s=String.format("%d/%d=%d", num1, num2, num1/num2);
	}
	
	Thread.sleep(3000);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=s %>
</body>
</html>