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
<style>
	#main{
		width:510px;
		height:130px;
		margin: 30px auto 0px;
	}
	
	#subject{
		font-size:20px;
		font-weight:bold;
	}
	
	input[name=sido]{
		font-size:20px;
		font-weight:bold;
		width:200px;
		height:30px;
	}
	
	#btnSido{
		height:35px;
	}
	
	.btnList{
		cursor:pointer;
	}
	
	.btnDel{
		cursor:pointer;
	}
	
	.btnDel2{
		cursor:pointer;
	}
	
</style>
</head>
<body>
<div id="main">
	<p id="subject">시도 및 도시 관리</p>
	<div>
		<input type="text" name="sido">
		<button type="button" id="btnSido">시도추가</button>
	</div>
		<c:forEach var="sidos" items="${sidoList }">
			<div style="border: 1px solid black; margin-top:5px;">
				<a class="btnList">${sidos.sido }</a>
				<a class="btnDel">삭제</a>
				<input type="hidden" id="A${sidos.snum }" value="${sidos.snum }">
				<div class="view"></div>
			</div>
		</c:forEach>
		<button type="button" id="theend">돌아가기</button>
</div>
<script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery-1.12.4.min.js"></script>
<script>
function list(snum, url, query){
	$.ajax({
		type:"post"
		,url:url
		,data:query
		,dataType:"json"
		,success:function(data){
			var out = "<input type='text' name='city'><button type='button' id='btnCitys' onclick='btncity()'>도시추가</button>";
			$.each(data.list, function(index, item){
				var snum = item.snum;
				var cnum = item.cnum;
				var city = item.city;
				
				
				out += "<p style='border:1px solid black'><a>"+city+"</a>&nbsp;<a class='btnDel2' onclick='btndel2("+cnum+")'>삭제</a></p>";
			});
			$(".view").empty();
			$("#A"+snum).siblings(".view").append(out);
			//console.log($("#A"+snum).val());
		}
		,error:function(e){
			console.log(e.responseText);
		}
	});
}

function btndel2(cnum){
	var url = "<%=cp%>/tour/deleteTcity";
	var query = "num="+cnum;
	
	var snum = $("#btnCitys").closest("div").siblings("input").val();
	var url2 = "<%=cp%>/tour/cityList";
	var query2 = "num="+snum;
	$.ajax({
		type:"post"
		,url:url
		,data:query
		,dataType:"json"
		,success:function(data){
			var state = data.state;
			if(state=="false"){
				alert("데이터를 추가하지 못했습니다.");
				return false;
			}
			list(snum, url2, query2);
		}
		,error:function(e){
			console.log(e.responseText);
		}
	});
}

function btncity(){
	var city = $("#btnCitys").siblings("input[name=city]").val();
	//var snum = $("#btnCitys").siblings("input[name=snum]").val();
	var snum = $("#btnCitys").closest("div").siblings("input").val();
	var url = "<%=cp%>/tour/insertTcity";
	var query = "num="+snum+"&city="+city;
	
	var url2 = "<%=cp%>/tour/cityList";
	var query2 = "num="+snum;
	
	$.ajax({
		type:"post"
		,url:url
		,data:query
		,dataType:"json"
		,success:function(data){
			var state = data.state;
			if(state=="false"){
				alert("데이터를 추가하지 못했습니다.");
				return false;
			}
			list(snum, url2, query2);
		}
		,error:function(e){
			console.log(e.responseText);
		}
	});
}

$(function(){
	$("#theend").click(function(){
		location.href="<%=cp%>/tour/main";
	});
	
	$(".btnList").click(function(){
		var snum = $(this).siblings("input").val();
		var url = "<%=cp%>/tour/cityList";
		var query = "num="+snum;
		list(snum, url, query);
	});
	

	
	$(".btnDel").click(function(){
		var snum = $(this).siblings("input").val();
		var url = "<%=cp%>/tour/deleteTsido";
		var query = "num="+snum;
		
		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data){
				var state = data.state;
				if(state=="false"){
					alert("데이터를삭제하지 못했습니다.");
					return false;
				}
				location.href="<%=cp%>/tour/manage";
			}
			,error:function(e){
				console.log(e.responseText);
			}
		});
	});
	
	$("#btnSido").click(function(){
		var sido = $("input[name=sido]").val();
		var url = "<%=cp%>/tour/insertTsido";
		var query = "sido="+sido;
		
		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data){
				var state = data.state;
				if(state=="false"){
					alert("데이터를 추가하지 못했습니다.");
					return false;
				}
				location.href="<%=cp%>/tour/manage";
			}
			,error:function(e){
				console.log(e.responseText);
			}
		});
	});
	
});


</script>
</body>
</html>