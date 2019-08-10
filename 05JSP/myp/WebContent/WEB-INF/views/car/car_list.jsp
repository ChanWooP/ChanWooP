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
		<a href="<%=cp%>/brand/list.do">◀</a>
		<a style="font-weight:bold; font-size:20px;">브랜드별 차량정보</a>
		<c:if test="${sessionScope.user.userId=='admin'}">
			<button class="btn" onclick="opens()">차량추가</button>
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
			<img src="<%=cp%>/uploads/car/${dto.carImage}" class="images" onclick="move(${dto.brandId},${dto.carId})">
			<p style="text-align:center; font-weight:bold;">${dto.carName}
			<c:if test="${sessionScope.user.userId=='admin'}">
				<a class="close2" onclick="del('${dto.brandId}','${dto.carId}')">X</a>
				<a class="update" onclick="opens2('${dto.carId}','${dto.carName}','${dto.carContent}','${dto.carImage}')">U</a>
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
			<h2>차량 추가하기</h2>
			<label for="addCarName">차량명</label>
			<input type="text" class="compTF" name="addCarName" maxlength="20" value="">
			<br>
			<label for="addCarContent">차량설명</label>
			<textarea name="addCarContent" rows="12" class="boxTA" style="width: 355px;"></textarea>
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
			<h2>차량 수정하기</h2>
			<label for="upCarName">차량명</label>
			<input type="text" class="compTF" name="upCarName" maxlength="20" value="">
			<br>
			<label for="upCarContent">차량설명</label>
			<textarea name="upCarContent" rows="12" class="boxTA" style="width: 355px;"></textarea>
			<br>
			<label for="upload">사진</label>
			<input type="file" name="upload" style="height: 25px;">
			<input type="text" name="upCarId" style="display:none;">
			<input type="text" name="carImage" style="display:none">
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

function opens2(id, name, content, image){
	$("input[name=upCarName]").val(name);
	$("textarea[name=upCarContent]").val(content);
	$("input[name=upCarId]").val(id);
	$("input[name=carImage]").val(image);
	
	$("#upModal").show();

}

function closes2(){
	$("#upModal").hide();
}

function up(){
	var f = document.upForm;
	
	var name = f.upCarName.value;
	var content = f.upCarContent.value;
	
	if(!name){
		alert("차량명을 입력하세요");
		f.upCarName.focus();
		return;
	}
	
	if(!content){
		alert("차량설명을 입력하세요");
		f.upCarContent.focus();
		return;
	}
	
	f.action="<%=cp%>/car/upCar.do?brandId=${brandId}";
	f.submit();
}


function add(){
	var f = document.addForm;
	
	var name = f.addCarName.value;
	var content = f.addCarContent.value;
	var file = f.upload.value;
	if(!name){
		alert("차량명을 입력하세요");
		f.addCarName.focus();
		return;
	}
	
	if(!content){
		alert("차량설명을 입력하세요");
		f.addCarContent.focus();
		return;
	}
	
	if(!file){
		alert("파일을 업로드하세요");
		f.upload.focus();
		return;
	}
	
	f.action="<%=cp%>/car/addCar.do?brandId=${brandId}";
	f.submit();
}

function del(brandId, carId){
	if(confirm("차량을 삭제하시겠습니까?")){
		location.href = "<%=cp%>/car/delCar.do?brandId="+brandId+"&carId="+carId;
	}
}

function move(brandId, carId){
	location.href = "<%=cp%>/detail/list.do?brandId="+brandId+"&carId="+carId;
}

</script>
</body>
</html>