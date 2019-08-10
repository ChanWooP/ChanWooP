<!DOCTYPE html>

<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	Calendar cal=Calendar.getInstance();
	String s=String.format("%tF %tA %tT", cal, cal, cal);
%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<p>안녕</p>
		<p><%=s%></p>
	</body>
</html>
