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
<script>
function createXMLHttpRequest(){
	var req = null;
	
	if(window.XMLHttpRequest){
		req = new XMLHttpRequest(); // IE7 이상, 크롬 등
	}else if(window.ActiveXObject){
		req = new XMLHttpRequest("Msxml2.XMLHTTP"); // IE6
	}
	
	return req;
}

var httpReq=null;

function sendOk(){
	var n1 = document.getElementById("num1").value;
	var n2 = document.getElementById("num2").value;
	var oper = document.getElementById("oper").value;
	
	var query = "num1="+n1+"&num2="+n2+"&oper="+oper;
	var url = "test2_ok.jsp?"+query;
	
	//Ajax 객체 생성
	httpReq = createXMLHttpRequest();
	
	//서버에서 처리하고 처리된 결과를 전송할 자바스크립트 함수 지정
	httpReq.onreadystatechange = callback;

	//Ajax 객체 Open
	httpReq.open("GET",url,true);
	
	//전송
	httpReq.send(null);
}

function callback(){
	// 모든 요청처리가 완료된 상태
	if(httpReq.readyState == 4){
		// 요청이 성공한 경우
		if(httpReq.status==200){
			printData();
		}
	}
}

function printData(){
	var lay = document.getElementById("resultLayout");
	
	// 서버에서 전송된 Text 넘겨 받기
	var result = httpReq.responseText;
	
	lay.innerHTML = result;
}
</script>
</head>
<body>
<form>
	<input type="text" name="num1" id="num1">
	<select name="oper" id="oper">
		<option value="add">더하기</option>
		<option value="sub">빼기</option>
		<option value="mul">곱하기</option>
		<option value="div">나누기</option>
	</select>
	<input type="text" name="num2" id="num2">
	<button type="button" onclick="sendOk()">연산</button>
</form>
<br>
<div id="resultLayout">
	
</div>
</body>
</html>