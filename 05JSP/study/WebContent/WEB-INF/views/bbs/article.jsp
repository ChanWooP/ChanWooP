<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>study</title>
<style type="text/css">
*{
    margin:0; padding: 0;
}
body {
    font-size:14px;
	font-family:"Malgun Gothic", "맑은 고딕", NanumGothic, 나눔고딕, 돋움, sans-serif;
}
a{
    color:#000000;
    text-decoration:none;
    cursor:pointer;
}
a:active, a:hover {
    text-decoration: underline;
    color:tomato;
}
textarea:focus, input:focus{
    outline: none;
}
.btn {
    color:#333333;
    font-weight:500;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
    border:1px solid #cccccc;
    background-color:#ffffff;
    text-align:center;
    cursor:pointer;
    padding:3px 10px 5px;
    border-radius:4px;
}
.btn:active, .btn:focus, .btn:hover {
    background-color:#e6e6e6;
    border-color:#adadad;
    color:#333333;
}
.boxTF {
    border:1px solid #999999;
    padding:3px 5px 5px;
    border-radius:4px;
    background-color:#ffffff;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
}
.selectField {
    border:1px solid #999999;
    padding:2px 5px 4px;
    border-radius:4px;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
}
.title {
    font-weight:bold;
    font-size:16px;
    font-family:나눔고딕, "맑은 고딕", 돋움, sans-serif;
}
</style>
<script>
function deleteBoard(num){
	if(confirm("게시물을 삭제하시겠습니까?")){
		var url = "<%=cp%>/bbs/delete.do?num="+ num +"&${query}";
		location.href = url;
	}
}
</script>
</head>

<body>

<table style="width: 600px; margin: 30px auto 0px; border-spacing: 0px;">
<tr height="40"> 
	<td align="left" class="title">
		<h3><span>|</span> 게시판</h3>
	</td>
</tr>
</table>

<table style="width: 600px; margin: 20px auto 0px; border-spacing: 0px; border-collapse: collapse;">
<tr height="35" style="border-top: 1px solid #cccccc; border-bottom: 1px solid #cccccc;">
    <td colspan="2" align="center">
	   ${dto.subject}
    </td>
</tr>

<tr height="35" style="border-bottom: 1px solid #cccccc;">
    <td width="50%" align="left" style="padding-left: 5px;">
       이름 : ${dto.name}
    </td>
    <td width="50%" align="right" style="padding-right: 5px;">
        ${dto.created} | 조회 ${dto.hitCount}
    </td>
</tr>

<tr style="border-bottom: 1px solid #cccccc;">
  <td colspan="2" align="left" style="padding: 10px 5px;" valign="top" height="200">
      ${dto.content}
   </td>
</tr>

<tr height="35" style="border-bottom: 1px solid #cccccc;">
    <td colspan="2" align="left" style="padding-left: 5px;">
    이전글 :
    <c:if test="${not empty preReadDTO}">
    	<a href="<%=cp %>/bbs/article.do?${query}&num=${preReadDTO.num}">${preReadDTO.subject}</a>
    </c:if>
    </td>
</tr>

<tr height="35" style="border-bottom: 1px solid #cccccc;">
    <td colspan="2" align="left" style="padding-left: 5px;">
    다음글 :
    <c:if test="${not empty nextReadDTO}">
    	<a href="<%=cp %>/bbs/article.do?${query}&num=${nextReadDTO.num}">${nextReadDTO.subject}</a>
    </c:if>
    </td>
</tr>

<tr height="35">
    <td colspan="2" align="right" style="padding-right: 5px;">
  	${dto.ipAddr}
    </td>
</tr>
</table>

<table style="width: 600px; margin: 0px auto 20px; border-spacing: 0px;">
<tr height="45">
    <td width="300" align="left">
        <button type="button" class="btn" onclick="javascript:location.href='<%=cp%>/bbs/update.do?num=${dto.num}&page=${page}';">수정</button>
        <button type="button" class="btn" onclick="deleteBoard('${dto.num}')">삭제</button>
    </td>

    <td align="right">
        <button type="button" class="btn" onclick="javascript:location.href='<%=cp %>/bbs/list.do?${query}';">리스트</button>
    </td>
</tr>
</table>

</body>
</html>
