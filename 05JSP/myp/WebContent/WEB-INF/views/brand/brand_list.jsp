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
		<a style="font-weight:bold; font-size:20px;">브랜드별 차량정보</a>
		<c:if test="${sessionScope.user.userId=='admin'}">
			<button class="btn" onclick="opens()">브랜드추가</button>
		</c:if>
		<hr width="600px">
		<table>
		<c:forEach var="dto" items="${list}" varStatus="status">
			<c:if test="${status.index==0}">
				<tr>
			</c:if>
			<c:if test="${status.index!=0 && status.index%5==0}">
				<c:out value="</tr><tr>" escapeXml="false"/>
			</c:if>
			<td>
			<img src="<%=cp%>/uploads/brand/${dto.brandImage}" class="images" onclick="move(${dto.brandId})">
			
			<p style="text-align:center; font-weight:bold;">
			${dto.brandName}
			<c:if test="${sessionScope.user.userId=='admin'}">
				<a class="close2" onclick="del(${dto.brandId})">X</a>
				<a class="close2" onclick="opens2('${dto.brandId}', '${dto.brandName }', '${dto.brandImage}')">U</a>
			</c:if>		
			</p>
			</td>
		</c:forEach>	
		</table>

		
	</div>
</div>
<div id="addModal" class="modal">
	<div class="modal-content">
		<form name="addForm" method="post" enctype="multipart/form-data">
			<span class="close" onclick="closes()">&times;</span>
			<h2>브랜드 추가하기</h2>
			<label for="addBranName">브랜드명</label>
			<input type="text" class="compTF" name="addBranName" maxlength="20">
			<br>
			<label for="upload">사진</label>
			<input type="file" name="upload" style="height: 25px;">
			<button type="button" class="compbtn" onclick="add()">완료</button>
		</form>
	</div>
</div>
<div id="upModal" class="modal">
	<div class="modal-content">
		<form name="upForm" method="post" enctype="multipart/form-data">
			<span class="close" onclick="closes2()">&times;</span>
			<h2>브랜드 수정하기</h2>
			<label for="upBranName">브랜드명</label>
			<input type="text" class="compTF" name="upBranName" maxlength="20">
			<br>
			<label for="upload">사진</label>
			<input type="file" name="upload" style="height: 25px;">
			<input type="text" name="upBrandId" style="display:none;">
			<input type="text" name="upBrandImage" style="display:none;">
			<button type="button" class="compbtn" onclick="up()">완료</button>
		</form>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function closes(){
	$("#addModal").hide();
}

function opens(){
	$("#addModal").show();
}

function closes2(){
	$("#upModal").hide();
}

function opens2(id, name, image){
	$("input[name=upBranName]").val(name);
	$("input[name=upBrandId]").val(id);
	$("input[name=upBrandImage]").val(image);
	
	$("#upModal").show();
}

function up(){
	var f = document.upForm;
	
	var name = f.upBranName.value;
	
	if(!name){
		alert("브랜드명을 입력하세요");
		f.upBranName.focus();
		return;
	}
	
	f.action="<%=cp%>/brand/upBrand.do";
	f.submit();
}

function add(){
	var f = document.addForm;
	
	var name = f.addBranName.value;
	var file = f.upload.value;
	
	if(!name){
		alert("브랜드명을 입력하세요");
		f.addBranName.focus();
		return;
	}
	
	if(!file){
		alert("파일을 업로드하세요");
		f.upload.focus();
		return;
	}
	
	f.action="<%=cp%>/brand/addBrand.do";
	f.submit();
}

function del(num){
	if(confirm("브랜드를 삭제하시겠습니까?")){
		location.href = "<%=cp%>/brand/delBrand.do?num="+num;
	}
}

function move(num){
	location.href = "<%=cp%>/car/list.do?brandId="+num;
}
</script>
</body>
</html>