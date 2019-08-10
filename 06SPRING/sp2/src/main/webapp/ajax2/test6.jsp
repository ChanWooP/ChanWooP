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
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(function(){
	$("#btnSend").click(function(){
		//var query = "num1="+$("#num1").val()+"&num2="+$("#num2").val()+"&oper="+encodeURIComponent($("#oper").val());
		var query = $("form[name=myForm]").serialize();
		var url = "test6_ok.jsp";
		
		// jquery AJAX - JSON
		$.ajax({
			type:"POST"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data){
				console.log(data)
				
				var out;
				out = "개수 : "+data.dataCount;
				//방법 1
				/* 
				$.each(data.list, function(index, item) {
					var num = item.num;
					var name = item.name;
					var content = item.content;
					
					out += "<br>번호:"+num+", 이름:"+name+"<br>";
					out += content+"<br>";
					out += "=================<br>";
				});
				$("#resultLayout").html(out);
				*/
				//방법2
				for(var idx=0; idx<data.list.length; idx++){
					var num = data.list[idx].num;
					var name = data.list[idx].name;
					var content = data.list[idx].content;
					
					out += "<br>번호:"+num+", 이름:"+name+"<br>";
					out += content+"<br>";
					out += "=================<br>";
				}
				
			}
			,error:function(e){ // 필요할떄만 쓰면 됨
				console.log(e.responseText);
			}
		});
	});
});
</script>
</head>
<body>
<form name="myForm">
	<p>이름 : <input type="text" name="name" id="name"></p>
	<p>내용 : <textarea rows="3" cols="50" name="content" id="content"></textarea></p>
	<p><button type="button" id="btnSend">보내기</button></p>
</form>
<hr>
<div id="resultLayout"></div>
</body>
</html>