<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function check() {
		var f = document.insaForm;

		if (!/^[가-힣]+$/.test(f.name.value)) {
			f.name.focus();
			return false;
		}
		if (!/^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/
				.test(f.birth.value)) {
			f.birth.focus();
			return false;
		}
		if (!/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/.test(f.phone.value)) {
			f.phone.focus();
			return false;
		}

		if (!/^(\d+)$/.test(f.basic.value)) {
			f.basic.focus();
			return false;
		}
		if (!/^(\d+)$/.test(f.pay.value)) {
			f.pay.focus();
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<div>
		<form name="insaForm" action="insa_ok.jsp" method="post"
			onsubmit="return check()">
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="text" name="birth"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="phone"></td>
				</tr>
				<tr>
					<td>기본급</td>
					<td><input type="text" name="basic"></td>
				</tr>
				<tr>
					<td>수당</td>
					<td><input type="text" name="pay"></td>
				</tr>
			</table>
			<button type="submit">등록하기</button>
		</form>
	</div>
</body>
</html>