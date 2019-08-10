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
<link rel="stylesheet" href="<%=cp%>/resource/css/style.css" type="text/css">
<link rel="stylesheet" href="<%=cp%>/resource/jquery/css/smoothness/jquery-ui.min.css" type="text/css">


<script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#btnCreateTableDialog").click(function(){
		$("#create-dialog").dialog({
			model:true
			,height:170
			,width:350
			,title:"테이블 작성"
			,buttons:{
				" 등록하기 ":function(){
					addTable();
				},
				" 닫기 ":function(){
					$(this).dialog("close");
				}
			},
			close:function(event, ui){
				$("#tableName").val("");
			}
		});
	});
	
	function addTable(){
		var tableName = $("#tableName").val().trim();
		if(! tableName){
			$("#tableName").focus();
			return;
		}
		
		var url = "<%=cp%>/table/request";
		var query = "tableName="+tableName;
		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dateType:"json"
			,success:function(data){
				var state=data.state;
				if(state=="true"){
					alert("테이블이 작성되었습니다.");
					$("#create-dialog").dialog("close");
				}else if(state=="false"){
					alert("테이블이 작성이 실패했습니다.")
				}
			}
		});
	}
});

</script>
</head>
<body>
<div style="width:700px; margin:30px auto;">
	<div>
		<h3>게시판 테이블</h3>
	</div>
	<div>
		<table style="width:100%; border-spacing: 0;">
			<tr height="35">
				<td width="50%">&nbsp;</td>
				<td align="right">
					<button id="btnCreateTableDialog" class="btn">테이블 작성</button>
				</td>
			</tr>
		</table>
	</div>
	<div id="create-dialog" style="display: none;">
		<div style="padding-top: 5px">
				<input type="text" id="tableName" class="boxTF" style="width:98%" placeholder="등록할 테이블명을 입력 하세요...">
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery-ui.min.js"></script>
</body>
</html>