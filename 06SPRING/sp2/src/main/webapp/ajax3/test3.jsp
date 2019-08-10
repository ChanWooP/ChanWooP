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
<script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery-1.12.4.min.js"></script>
<script>
	jQuery.fn.center=function(){
		this.css("position","absolute");
		this.css("top", Math.max(0,($(window).height()-$(this).outerHeight())/2+$(window).scrollTop())+"px");
		this.css("left", Math.max(0,($(window).width()-$(this).outerWidth())/2+$(window).scrollLeft())+"px");
		
		return this;
	};
	
	$(function(){
		$("#btnSend").click(function(){
			var q = "num="+$("#num").val();
			var url = "test3_ok.jsp";
			
			$.ajax({
				type:"post"
				,url:url
				,data:q
				,success:function(data){
					$("#resultLayout").html("결과:"+data); // 1
				}
				,error:function(e){
					console.log(e.responseText);
				}
			});
			$("#resultLayout").html("AJAX 실행 결과 후 호출됨");// 2
			
		});
		
		$(document)
			.ajaxStart(function(){ // AJAX 시작시
				$("#loading").center();
				$("#loadingLayout").fadeTo("slow", 0.5); // 불투명모드
				$("#resultLayout").hide();
			})
			.ajaxComplete(function(){ // AJAX 종료시
				$("#loadingLayout").hide();
				$("#resultLayout").show();
			})
	});
</script>
</head>
<body>
<p>
	<input type="text" id="num">
	<button type="button" id="btnSend">결과</button>
</p>
<div id="resultLayout"></div>
<div id="loadingLayout" style="display:none; position: absolute; left:0; top:0; width:100%; height:100%; z-index:9000; background: #eeeeee;">
	<img id="loading" src="<%=cp%>/resource/images/loading.gif" border="0">
</div>
</body>
</html>