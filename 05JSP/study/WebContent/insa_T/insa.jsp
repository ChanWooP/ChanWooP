<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	margin: 0px;
}
body {
	font-size: 14px;
	font-family: 맑은 고딕, 굴림;
}
.btn {
	 font-size: 12px;
	 color:#333;
 	 font-weight:500;
	 font-family: NanumGothic, 나눔고딕, "Malgun Gothic", "맑은 고딕", 돋움, sans-serif;
	 border:1px solid #ccc;
	 background-color:#FFF;
	 vertical-align:middle;
	 text-align:text-align;
	 cursor:cursor;
	 padding:4px 8px;
	 border-radius:4px;
	 margin-bottom: 3px;
}
.btn:active, .btn:focus, .btn:hover {
	 background-color:#e6e6e6;
	 border-color: #adadad;
	 color: #333;
}
.boxTF {
	border:1px solid #999;
	padding:4px 6px;
	border-radius:4px;
	background-color:#ffffff;
	font-family: NanumGothic, 나눔고딕, "Malgun Gothic", "맑은 고딕", 돋움, sans-serif;
	font-size: 9pt;
}
</style>

<script type="text/javascript">
function isValidDate(data) {
    var regexp = /[12][0-9]{3}[\.|\-|\/]?[0-9]{2}[\.|\-|\/]?[0-9]{2}/;
    if(! regexp.test(data))
        return false;

    regexp=/(\.)|(\-)|(\/)/g;
    data=data.replace(regexp, "");
    
	var y=parseInt(data.substr(0, 4));
    var m=parseInt(data.substr(4, 2));
    if(m<1||m>12) 
    	return false;
    var d=parseInt(data.substr(6));
    var lastDay = (new Date(y, m, 0)).getDate();
    if(d<1||d>lastDay)
    	return false;
		
	return true;
}

function isValidKorean(data){
    // var format = /^[\uac00-\ud7a3]*$/g;
	var format = /^[가-힣]+$/;
    
    
   return format.test(data);
}

function sendOk() {
	var f=document.insaForm;
	var format;
	
    if(! isValidKorean(f.name.value)) {
        f.name.focus();
        return;
    }
    
    if(! isValidDate(f.birth.value)) {
        f.birth.focus();
        return;
    }
    
    format=/^(01[016789])-[0-9]{3,4}-[0-9]{4}$/g;
    if(! format.test(f.tel.value)) {
        f.tel.focus();
        return;
    }
    
    // format=/^(\d+)$/;
    format=/^\d{6,8}$/;
    if(! format.test(f.basic.value)) {
        f.basic.focus();
        return;
    }
    
    format=/^\d{1,7}$/;
    if(! format.test(f.sudang.value)) {
        f.sudang.focus();
        return;
    }
	
    f.submit();
}
</script>

</head>
<body>

<form name="insaForm" action="insa_ok.jsp" method="post">
	<table style="width: 300px; margin: 30px auto; border-collapse: collapse; border-spacing: 0;">
		<caption>인사관리</caption>
		<tr height="35" style="border-top: 1px solid #999999; border-bottom: 1px solid #999999;">
			<td width="100" align="center">이름</td>
			<td>
				<input type="text" name="name" class="boxTF">
			</td>
		</tr>
		<tr height="35" style="border-top: 1px solid #999999; border-bottom: 1px solid #999999;">
			<td width="100" align="center">생년월일</td>
			<td>
				<input type="text" name="birth" class="boxTF">
			</td>
		</tr>	
		<tr height="35" style="border-top: 1px solid #999999; border-bottom: 1px solid #999999;">
			<td width="100" align="center">전화번호</td>
			<td>
				<input type="text" name="tel" class="boxTF">
			</td>
		</tr>	
		<tr height="35" style="border-top: 1px solid #999999; border-bottom: 1px solid #999999;">
			<td width="100" align="center">기본급</td>
			<td>
				<input type="text" name="basic" class="boxTF">
			</td>
		</tr>	
		<tr height="35" style="border-top: 1px solid #999999; border-bottom: 1px solid #999999;">
			<td width="100" align="center">수당</td>
			<td>
				<input type="text" name="sudang" class="boxTF">
			</td>
		</tr>
		<tr height="45" align="center">
			<td colspan="2">
				<button type="button" onclick="sendOk()" class="btn">등록하기</button>
			</td>
		</tr>
	</table>
</form>

</body>
</html>