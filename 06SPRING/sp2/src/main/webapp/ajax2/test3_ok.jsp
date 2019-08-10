<%@page import="java.net.URLDecoder"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
	
	int num1 = Integer.parseInt(request.getParameter("num1"));
	int num2 = Integer.parseInt(request.getParameter("num2"));
	String oper = request.getParameter("oper");
	oper = URLDecoder.decode(oper, "UTF-8");
	
	String s = "";
	if(oper.equals("+")){
		s=String.format("%d+%d=%d", num1, num2, num1+num2);
	}else if(oper.equals("-")){
		s=String.format("%d-%d=%d", num1, num2, num1-num2);
	}else if(oper.equals("*")){
		s=String.format("%d*%d=%d", num1, num2, num1*num2);
	}else if(oper.equals("/")){
		s=String.format("%d/%d=%d", num1, num2, num1/num2);
	}

%>
<%=s%>