<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="vo" class="com.insa.InsaVO"/>
<jsp:setProperty property="*" name="vo"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>이름 : ${vo.name} </div>
<div>생년월일 : ${vo.birth} </div>
<div>나이 : ${vo.age} </div>
<div>띠 : ${vo.tti} </div>
<div>기본급 : ${vo.basic} </div>
<div>수당 : ${vo.sudang} </div>
<div>세금 : ${vo.tax} </div>
<div>총급여 : ${vo.pay} </div>
<div>총급여 : ${vo.spay} </div>
<div>총급여 : <fmt:formatNumber value="${vo.pay}" type="currency"/>  </div>
<div>실급여 : ${vo.pay-vo.tax} </div>

</body>
</html>

