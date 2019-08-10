<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CIS</title>
<link rel="shortcut icon" href="<%=cp%>/resource/img/favicon.ico">
<link rel="stylesheet" href="<%=cp%>/resource/css/style.css" type="text/css">
</head>
<body>
<div class="main">
	<div class="header">
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</div>
	<div class="container">
		<a style="font-weight:bold; font-size:20px;">시승기</a>
		<hr width="600px">
	<table style="width: 100%; margin: 20px auto 0px; border-spacing: 0px; border-collapse: collapse;">
			<tr height="35" style="border-top: 1px solid #cccccc; border-bottom: 1px solid #cccccc;">
			    <td colspan="2" align="center">
				   ${dto.subject}
			    </td>
			</tr>
			
			<tr height="35" style="border-bottom: 1px solid #cccccc;">
			    <td width="50%" align="left" style="padding-left: 5px;">
			       이름 : ${dto.userName}
			    </td>
			    <td width="50%" align="right" style="padding-right: 5px;">
			        ${dto.created} | 조회 ${dto.hitCount}
			    </td>
			</tr>
			<tr>
				<td>
					<img src="<%=cp%>/uploads/gallary/${dto.galleryimage}" width="300" height="300">
				</td>
			</tr>
			<tr style="border-bottom: 1px solid #cccccc;">
			  <td colspan="2" align="left" style="padding: 10px 5px;" valign="top" height="200">
			      ${dto.content_}
			   </td>
			</tr>

			</table>
			<table style="width: 100%; margin: 0px auto 20px; border-spacing: 0px;">
			<tr height="45">
			    <td width="300" align="left">
			       <c:if test="${sessionScope.user.userId==dto.userId}">				    
			          <button type="button" style="float:left; margin-right:5px;" class="btn" onclick="updateBoard('${dto.boardId}','${dto.galleryimage }');">수정</button>
			       </c:if>
			       <c:if test="${sessionScope.user.userId==dto.userId || sessionScope.member.userId=='admin'}">				    
			          <button type="button" style="float:left; margin-right:5px;" class="btn" onclick="deleteBoard('${dto.boardId}','${dto.galleryimage }');">삭제</button>
			       </c:if>
			    </td>
			
			    <td align="right">
			        <button type="button" class="btn" onclick="javascript:location.href='<%=cp%>/bbs/list.do?${query}';">리스트</button>
			    </td>
			</tr>
			</table>
	</div>
</div>
<script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
function deleteBoard(boardId, image) {
<c:if test="${sessionScope.user.userId=='admin' || sessionScope.user.userId==dto.userId}">
    var url = "<%=cp%>/bbs/delete.do?boardId="+ boardId+"&image="+image+"&page="+${page};

    if(confirm("위 자료를 삭제 하시 겠습니까 ? "))
    	location.href=url;
</c:if>    
<c:if test="${sessionScope.user.userId!='admin' && sessionScope.user.userId!=dto.userId}">
    alert("게시물을 삭제할 수  없습니다.");
</c:if>
}

function updateBoard(boardId, image) {
<c:if test="${sessionScope.user.userId==dto.userId}">
    var page = "${page}";
    var query = "boardId="+boardId+"&page="+page;
    var url = "<%=cp%>/bbs/update.do?" + query;

    location.href=url;
</c:if>

<c:if test="${sessionScope.user.userId!=dto.userId}">
   alert("게시물을 수정할 수  없습니다.");
</c:if>
}
</script>
</body>
</html>