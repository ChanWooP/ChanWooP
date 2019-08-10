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
</head>
<body>

<form action="<%=cp%>/user3/request" method="post">
<p>
  취미 : <input type="checkbox" name="hobby" value="영화" id="h1"> <label for="h1">영화보기</label>
       <input type="checkbox" name="hobby" value="게임" id="h2"> <label for="h2">게임하기</label>
       <input type="checkbox" name="hobby" value="음악" id="h3"> <label for="h3">음악감상</label>
       <input type="checkbox" name="hobby" value="운동" id="h4"> <label for="h4">운동</label>
</p>
<p><button type="submit"> 결과  </button></p>
</form>

</body>
</html>