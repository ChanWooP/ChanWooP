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
		<form name="boardForm" method="post" enctype="multipart/form-data">
			<a style="font-weight:bold; font-size:20px;">글쓰기</a>
			<hr width="600px">
			<p>작성자</p>
			<p>${sessionScope.user.userName}</p>
			<p>제목</p>
			<input type="text" name="subject" class="boxTF" style="width:95%;" value="${dto.subject }">
			<p>나의차량사진</p>
			<input type="file" name="upload" class="boxTF" style="width:95%;">
			<p>내용</p>
			<textarea name="content" rows="12" class="boxTA" style="width: 95%;">${dto.content_}</textarea>
			
			<c:if test="${mode=='update'}">
				<input type="hidden" name="image" value="${dto.galleryimage }">
				<input type="hidden" name="boardId" value="${dto.boardId}">
				<input type="hidden" name="page" value="${page}">
			</c:if>
			<button type="button" style="float:left; margin-top:10px; margin-right:5px;" class="btn" onclick="sendBoard();">${mode=='update'?'수정완료':'등록하기'}</button>
			<button type="button" style="float:left; margin-top:10px; margin-right:5px;" class="btn" onclick="javascript:location.href='<%=cp%>/bbs/list.do';">${mode=='update'?'수정취소':'등록취소'}</button>
		<button type="reset" style="float:right; margin-top:10px; " class="btn">다시입력</button>
		</form>
	</div>
</div>
<script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery-1.12.4.min.js"></script>
<script>
function sendBoard(){
    var f = document.boardForm;
    var mode="${mode}";
	var str = f.subject.value;
    if(!str) {
        alert("제목을 입력하세요. ");
        f.subject.focus();
        return;
    }

	str = f.content.value;
    if(!str) {
        alert("내용을 입력하세요. ");
        f.content.focus();
        return;
    }
	
	str = f.upload.value;
    if(mode=="created" && !str) {
        alert("파일을 업로드하세요. ");
        f.upload.focus();
        return;
    }
    
	
	if(mode=="created")
		f.action="<%=cp%>/bbs/created_ok.do";
	else if(mode=="update")
		f.action="<%=cp%>/bbs/update_ok.do";

    f.submit();
}
</script>
</body>
</html>