<%@page import="org.json.JSONObject"%>
<%@page import="java.util.concurrent.atomic.AtomicLong"%>
<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%!
	private AtomicLong count = new AtomicLong(0);
%>
<%
	String cp=request.getContextPath();

	long num = count.incrementAndGet();
	String s = String.format("%tT",Calendar.getInstance());
	
	JSONObject job = new JSONObject();
	job.put("num", num);
	job.put("time", s);
	
	out.print(job.toString());
%>
