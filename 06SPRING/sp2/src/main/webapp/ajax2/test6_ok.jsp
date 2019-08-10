<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
	String name = request.getParameter("name");
	String content = request.getParameter("content");
	
	JSONObject job = new JSONObject();
	job.put("dataCount", 5);

	JSONArray jarr = new JSONArray();
	for(int n=0; n<=5; n++){
		JSONObject ob = new JSONObject();
		ob.put("num",n);
		ob.put("name",name+"-"+n);
		ob.put("content",content+"-"+n);
		
		jarr.put(ob);
	}
	job.put("list", jarr);
	
	System.out.println(job.toString());
	
	out.print(job.toString());
%>
