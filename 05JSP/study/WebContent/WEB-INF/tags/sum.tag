<%@ tag body-content="scriptless" pageEncoding="UTF-8"%>
<%@ tag trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="begin" required="true" type="Integer"%>
<%@ attribute name="end" required="true" type="Integer"%>

<%@ variable name-given="sum" variable-class="java.lang.String"
	scope="NESTED"%>

<%--
	body-content="scriptless" : 몸체가 존재하는 태그
	<jsp:doBody/> : 몸체로 전달받은 내용을 그대로 출력
	variable name-given : 이 태그를 호출할 페이지에 추가할 변수명을 정의
--%>

<c:set var="sum" value="0"/>
<c:forEach var="n" begin="${begin}" end="${end}">
	<c:set var="sum" value="${sum+n}"/>
</c:forEach>
<jsp:doBody/>