<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{
    margin: 0px; padding: 0px;
}
body {
    font-size: 14px;
    font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
    box-sizing: border-box;  /* padding과 border는 크기에 포함되지 않음 */    
}
.title {
    width:100%;
    height:45px;
    line-height:45px;
    text-align:left;
    font-weight: bold;
    font-size:15px;
}
</style>

</head>
<body>

<div style="width: 400px; margin: 30px auto 0px;">

    <div style="title">
       <h3><span>|</span> 쪽지 보내기 결과</h3>
    </div>

    <table style="width: 100%; margin: 10px auto 0px;">
    <tr height="30">
        <td width="100">받는사람</td>
        <td>
        	<c:forEach var="vo" items="${dto.recipient}">
        		<span style="margin-right: 5px;">${vo}</span>
        	</c:forEach>
        </td>
    </tr>
    
    <tr>
        <td valign="top" style="margin-top: 5px;">메시지</td>
        <td>
        	<span style="white-space:pre;">${dto.msg}</span>
        </td>
    </tr>
    </table>
</div>

</body>
</html>