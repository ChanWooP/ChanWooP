<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>default locale : <%=response.getLocale() %></p>
<p>default locale : ${pageContext.response.locale }</p>
<p>통화 : <fmt:formatNumber value="12345.825" type="currency"/></p>
<hr>

<p>ja_JP로 로케일 변경 <fmt:setLocale value="ja_JP"/></p>
<p>통화 : <fmt:formatNumber value="12345.825" type="currency"/></p>
<hr>

<p>en_US로 로케일 변경 <fmt:setLocale value="en_US"/></p>
<p>통화 : <fmt:formatNumber value="12345.825" type="currency"/></p>
<hr>

<p>ko_KR로 로케일 변경 <fmt:setLocale value="ko_KR"/></p>
<p>통화 : <fmt:formatNumber value="12345.825" type="currency"/></p>
<hr>

<p>기본 : <fmt:formatNumber value="12345.825"/></p>
<p>number : <fmt:formatNumber value="12345.825" type="number"/></p>
<p>currency: <fmt:formatNumber value="12345.825" type="currency"/></p>
<p>currency: <fmt:formatNumber value="12345.825" type="currency" currencySymbol="W"/></p>
<p>percent: <fmt:formatNumber value="0.789" type="percent"/></p>
<hr>

<p>패턴 : <fmt:formatNumber value="12345.825" pattern="#,##0.0"/></p>
<p>패턴 : <fmt:formatNumber value="12345.825" pattern="W#,###.0"/></p>
<p>패턴 : <fmt:formatNumber value="0.789" pattern="#,##0.0"/></p>
<p>패턴 : <fmt:formatNumber value="0.789" pattern="#,###.0"/></p>
<hr>

<c:set var="now" value="<%=new java.util.Date()%>"/>
<p>현재시간 : ${now}</p>
<p>date : <fmt:formatDate value="${now}" type="date"/></p>
<p>time : <fmt:formatDate value="${now}" type="time"/></p>
<p>both : <fmt:formatDate value="${now}" type="both"/></p>
<p>full : <fmt:formatDate value="${now}" type="both"
			dateStyle="full" timeStyle="full"/></p>
<hr>

<c:set var="str" value="seoul korea"/>
<p>${fn:startsWith(str, "seoul") ? "seoul 로 시작" : "seoul로 시작하지 안음" }</p>
<p>${fn:endsWith(str, "korea") ? "korea 로 시작" : "korea로 시작하지 안음" }</p>
<p>${fn:replace(str, "seoul","서울") }</p>
<p>${fn:toUpperCase(str) }</p>
</body>
</html>