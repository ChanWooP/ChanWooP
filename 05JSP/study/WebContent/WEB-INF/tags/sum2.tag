<%@ tag body-content="scriptless" pageEncoding="UTF-8"%>
<%@ tag trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="var" required="true" rtexprvalue="false" type="java.lang.String" %>
<%@ attribute name="begin" required="true" type="Integer"%>
<%@ attribute name="end" required="true" type="Integer"%>

<%@ variable name-from-attribute="var" alias="result" 
	variable-class="java.lang.Integer" scope="AT_END"%>

<%--

--%>

<%
	int s=0;
	for(int i=begin; i<=end; i++)
		s+=i;
%>
<c:set var="result" value="<%=s %>"/>
