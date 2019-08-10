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
	if(oper.equals("+")){
		s=String.format("%d", num1+num2);
	}else if(oper.equals("-")){
		s=String.format("%d", num1-num2);
	}else if(oper.equals("*")){
		s=String.format("%d", num1*num2);
	}else if(oper.equals("/")){
		s=String.format("%d", num1/num2);
	}
	
	// JSON 형식은 -> {"키1":"값1", "키2":"값2"}
	// JSON 형식배열 -> [{"키1":"값1", "키2":"값2"}, {"키1":"값1", "키2":"값2"}]
	StringBuilder sb = new StringBuilder();
	sb.append("{");
	sb.append("\"num1\":\""+num1+"\"");
	sb.append(",\"num2\":\""+num2+"\"");
	sb.append(",\"oper\":\""+oper+"\"");
	sb.append(",\"result\":\""+s+"\"");
	sb.append("}");
	
	out.print(sb.toString());
%>
