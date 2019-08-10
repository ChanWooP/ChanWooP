<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>산술</title>
<script>
	function check(){
		var f = document.calcForm;
		
		// ^:앞 / $:뒤 / \d:숫자 / +:한 자 이상
		if(! /^(\d+)$/.test(f.num1.value)){
			f.num1.focus();
			return false;
		}
		if(! /^(\d+)$/.test(f.num2.value)){
			f.num2.focus();
			return false;
		}
		
		// f.submit(); // 호출하면 안됨
			// submit 버튼은 submit() 함수를 호출하면 서버로 두 번 전송되므로
			// 절대로 submit() 함수를 호출하면 안된다.
		return true;
	}
</script>
</head>
<body>
	<form name="calcForm" action="write_ok.jsp" method="post" onsubmit="return check()">
		<input type="text" name="num1">
		<select name="operator">
			<option value="+">더하기</option>
			<option value="-">빼기</option>
			<option value="*">곱하기</option>
			<option value="/">나누기</option>
		</select>
		<input type="text" name="num2">
		<button type="submit">연산</button>
	</form>
</body>
</html>
