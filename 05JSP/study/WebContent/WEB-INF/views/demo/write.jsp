<%@ page contentType="text/html; charset=UTF-8"%>
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
	<form action="<%=cp %>/demo" method="post" >
		<p>이름 : <input type="text" name="name"></p>	
		<p>제목 : <input type="text" name="subject"></p>
		<p>
			<button type="submit">보내기</button>
		</p>	
	</form>
</body>
</html>