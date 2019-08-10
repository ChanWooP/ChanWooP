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
	var name = document.getElementById("name").value;
	var content = document.getElementById("content").value;
	
	var query = "name="+name+"&content="+encodeURIComponent(content);
	var url = "test5_ok.jsp";
	
	//Ajax 객체 생성
	httpReq = createXMLHttpRequest();
	
	//서버에서 처리하고 처리된 결과를 전송할 자바스크립트 함수 지정
	httpReq.onreadystatechange = callback;

	//Ajax 객체 Open(POST)
	httpReq.open("POST",url,true);
	httpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	//전송
	httpReq.send(query);
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
	var xmlDoc = httpReq.responseXML;
	
	var root = xmlDoc.getElementsByTagName("root")[0];
	var dataCount = xmlDoc.getElementsByTagName("dataCount")[0].firstChild.nodeValue;
	
	var out = "데이터 개수 : "+ dataCount + "<br>";
	
	var records = root.getElementsByTagName("record");
	
	for(var i=0; i<records.length; i++){
		var item = records[i];
		var num = item.getAttribute("num");
		var name = item.getElementsByTagName("name")[0].firstChild.nodeValue;
		var content = item.getElementsByTagName("content")[0].firstChild.nodeValue;
		out += "게시물번호 : "+num+", 작성자 : "+name+"<br>";
		out += content + "<br>";
		out += "=====================================<br>";
	}
	lay.innerHTML = out;
}
</script>
</head>
<body>
<form>
	<p>이름 : <input type="text" name="name" id="name"></p>
	<p>내용 : <textarea rows="3" cols="50" name="content" id="content"></textarea></p>
	<p><button type="button" onclick="sendOk()">등록하기</button></p>
</form>

<div id="resultLayout"></div>

</body>
</html>