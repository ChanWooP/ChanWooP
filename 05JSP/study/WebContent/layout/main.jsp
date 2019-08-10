<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="style.css" type="text/css">

</head>
<body>

<div id="main-layout">
	<div id="header">
		<jsp:include page="header.jsp">
			<jsp:param value="main" name="menuItem"/>
		</jsp:include>
	</div>
	<div id="body-content">
		메인 화면 입니다.
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp"/>
	</div>
</div>


</body>
</html>