<%@ page contentType="text/html; charset=UTF-8"%>
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
<title>CIS</title>
<link rel="shortcut icon" href="<%=cp%>/resource/img/favicon.ico">
<link rel="stylesheet" href="<%=cp%>/resource/css/style.css" type="text/css">
<style>
.tt{
	margin-left:10px;
	text-align:center;
	border-top:2px solid blue;
	border-bottom:2px solid blue;
	border-spacing: 0px;
}

.tt td{
	text-align:left;
	border-bottom:1px solid gray;
	padding-left:20px;
}

.tt th{
	text-align:center;
	border-bottom:1px solid blue;
	padding:10px;
}
</style>
</head>
<body>
<div class="main">
	<div class="header">
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</div>
	<div class="container">
		<a href="<%=cp%>/car/list.do?brandId=${brandId}&carId=${carId}">◀</a>
		<a style="font-weight:bold; font-size:20px;">브랜드별 차량정보</a>
		<c:if test="${sessionScope.user.userId=='admin'}">
			<button class="btn" onclick="opens()">상세추가</button>
		</c:if>
		<hr width="600px"><br>
		<table>
			<tr height="600px" style="vertical-align:top">
				<td style="border-right:1px solid black">
					<img src="<%=cp%>/uploads/car/${dto.carImage}" class="images">
					<p style="text-align:center; font-weight:bold;">${dto.carName}</p>
				</td>
				<td>
					<table class="tt">
						<tr>
							<th>바디 형식</th>
							<th>세부 모델명</th>
							<th>소비자 가격</th>
							<c:if test="${sessionScope.user.userId=='admin'}">
								<th></th>
							</c:if>
						</tr>
						<c:forEach var="dtoD" items="${list }">
							<tr>
								<td>${dtoD.bodyId }</td>
								<td>${dtoD.detailName }</td>
								<td>${dtoD.detailPrice }</td>
								<td style="display:none;">${dtoD.bodyId2 }</td>
								<td style="display:none;">${dtoD.detailId }</td>
								<c:if test="${sessionScope.user.userId=='admin'}">
									<td class="close2" onclick="del(${brandId},${carId},${dtoD.detailId})">&times;</td>
									<td class="update">U</td>
								</c:if>
								
							</tr>
						</c:forEach>
					</table>
					<p style="margin-left:10px; margin-top:20px; margin-bottom:20px;">상세 내용</p>
					<div style="overflow:auto; width:600px; height:200px; border:1px solid gray; margin-left:10px">
						${dto.carContent }
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>
<div id="addModal" class="modal">
	<div class="modal-content">
		<form name="addForm" method="post">
			<span class="close" onclick="closes()">&times;</span>
			<h2>상세 추가하기</h2>
			<label for="addDetailBody">바디 형식</label>
				<select id="bodyList" name="bodyList" class="compTF"> 
					<c:forEach var="map" items="${bodyList}" varStatus="status">
						<option value="${map.get('bodyId')}">${map.get('bodyName')}</option>  
					</c:forEach>
				</select>
			<br>
			<label for="addDetailName">세부 모델명</label>
			<input type="text" class="compTF" name="addDetailName" value="">
			<br>
			<label for="addDetailPrice">소비자 가격</label>
			<input type="text" class="compTF" name="addDetailPrice" value="">
			<br>
			<button type="button" class="compbtn" onclick="add(${brandId},${carId})">완료</button>
		</form>
	</div>
</div>
<div id="upModal" class="modal">
	<div class="modal-content">
		<form name="upForm" method="post">
			<span class="close" onclick="closesU()">&times;</span>
			<h2>상세 수정하기</h2>
			<label for="addDetailBody">바디 형식</label>
				<select id="bodyList2" name="bodyList2" class="compTF"> 
					<c:forEach var="map" items="${bodyList}" varStatus="status">
						<option value="${map.get('bodyId')}">${map.get('bodyName')}</option>  
					</c:forEach>
				</select>
			<br>
			<label for="upDetailName">세부 모델명</label>
			<input type="text" class="compTF" name="upDetailName" value="">
			<br>
			<label for="upDetailPrice">소비자 가격</label>
			<input type="text" class="compTF" name="upDetailPrice" value="">
			<br>
			<input type="text" style="display:none;" name="upDetailId">
			<button type="button" class="compbtn" onclick="up(${brandId},${carId})">완료</button>
		</form>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function closesU(){
	$("#upModal").hide();
}

$(function(){
	var arr=[];
	$("body").on("click",".update", function(){
		var tds = $(this).closest("tr").children("td");
		$(tds).each(function(i){
			if(i!=tds.length-1){
				arr[i] = $(this).text();
				if(i==1){
					$("input[name=upDetailName]").val(arr[i]);
				}else if(i==2){
					$("input[name=upDetailPrice]").val(arr[i]);
				}else if(i==3){
					$("#bodyList2").val(arr[i]);
				}else if(i==4){
					$("input[name=upDetailId]").val(arr[i]);
				}
			}
		});
		$("#upModal").show();
	});
});

function closes(){
	$("#addModal").hide();
}

function opens(){
	$("#addModal").show();
}

function up(){
	opens();
}

function del(brandId, carId, detailId){
	if(confirm("세부목록을 삭제 하시겠습니까?"))
		location.href="<%=cp%>/detail/delDetail.do?brandId="+brandId+"&carId="+carId+"&detailId="+detailId;
}

function add(brandId, carId){
	var f = document.addForm;
	
	var name = f.addDetailName.value;
	var price = f.addDetailPrice.value;
	
	if(! name){
		alert("세부 모델명을 입력하세요");
		f.addDetailName.focus();
		return;
	}
	
	if(! price){
		alert("소비자 가격을 입력하세요");
		f.addDetailPrice.focus();
		return;
	}
	
	f.action = "<%=cp%>/detail/addDetail.do?brandId="+brandId+"&carId="+carId;
	f.submit();
	
}

function up(brandId, carId){
	var f = document.upForm;
	
	var name = f.upDetailName.value;
	var price = f.upDetailPrice.value;
	var id = f.upDetailId.value;

	if(! name){
		alert("세부 모델명을 입력하세요");
		f.upDetailName.focus();
		return;
	}
	
	if(! price){
		alert("소비자 가격을 입력하세요");
		f.upDetailPrice.focus();
		return;
	}
	
	f.action = "<%=cp%>/detail/upDetail.do?brandId="+brandId+"&carId="+carId+"&detailId="+id;
	f.submit();
	
}
</script>
</body>
</html>