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

<style type="text/css">
* {
    margin:0; padding:0;
}
body {
    font-family:"Malgun Gothic", "맑은 고딕", NanumGothic, 나눔고딕, 돋움, sans-serif;
    font-size: 14px;
}
a {
    cursor: pointer;
    color: #000000;
    text-decoration: none;
}
a:hover, a:active {
    color: #F28011;
    text-decoration: underline;
}

.title {
	font-weight: bold;
	font-size:13pt;
	margin-bottom:10px;
	font-family: 나눔고딕, "맑은 고딕", 돋움, sans-serif;
}

ul {
	list-style: none;
}

.btn {
    color:#333333;
    font-weight:500;
    font-family:"Malgun Gothic", "맑은 고딕", NanumGothic, 나눔고딕, 돋움, sans-serif;
    border:1px solid #cccccc;
    background-color:#fff;
    text-align:center;
    cursor:cursor;
    padding:3px 10px 5px;
    border-radius:4px;
}
.btn:active, .btn:focus, .btn:hover {
    background-color:#e6e6e6;
    border-color: #adadad;
    color: #333333;
}
.boxTF {
    border:1px solid #999999;
    padding:3px 5px 5px;
    border-radius:4px;
    background-color:#ffffff;
    font-family:"Malgun Gothic", "맑은 고딕", NanumGothic, 나눔고딕, 돋움, sans-serif;
}
.selectField {
    border:1px solid #999999;
    padding:2px 5px 4px;
    border-radius:4px;
    font-family:"Malgun Gothic", "맑은 고딕", NanumGothic, 나눔고딕, 돋움, sans-serif;
}

.btnDelete{
    display: inline-block; width: 50px; text-align: right; cursor: pointer;
}

.sidoLayout{
    border: 1px solid #ccc; margin: 5px 0px; padding: 5px;
}
.sido{
    display: inline-block; width: 220px; cursor: pointer;
}

.cityLayout{
    border-top: 1px solid #ccc; padding: 5px 5px 0px;
    display: none;
}
.city{
    display: inline-block; width: 200px;
}
</style>

<script type="text/javascript" src="<%=cp%>/resource/jquery/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(function(){
    $("body").on("click", ".sido", function(){
    	var $listCity = $(this).closest(".sidoLayout").children().children(".listCity");
    	var isHidden = $(this).parent().next().is(':hidden');
    	var snum=$(this).closest(".sidoLayout").attr("data-snum");
    	$(".cityLayout").hide();
    	
    	if(isHidden) {
    		$(this).parent().next().show();
    		
    		var url="<%=cp%>/tour/cityList";
    		var query="snum="+snum;

    		$.ajax({
    			type:"post"
    			,url:url
    			,data:query
    			,dataType:"json"
    			,success:function(data){
    				$listCity.empty();
    				
    				var out="";
    				for(var i=0; i<data.list.length; i++) {
    					var cnum=data.list[i].cnum;
    					var city=data.list[i].city;
    					
    					if(i==0)
    						out+="<div class='city' style='border: 1px solid #cccccc; padding: 5px;'>";
    					else
    						out+="<div class='city' style='border: 1px solid #cccccc; padding: 5px; border-top:none;'>";
    					out+="      <span class='cityName'>"+city+"</span>&nbsp;";
    					out+="      <span class='btnDelete btnCityDelete' data-cnum='"+cnum+"'>삭제</span>";
    					out+="    </div>";
    				}
    				
    				$listCity.html(out);
    			}
    		    ,error:function(e) {
    		    	console.log(e.responseText);
    		    }
    		});
		} else {
			$(this).parent().next().hide();
		}
    });

/*	
   // 동적이 아닌 경우 
	$(".sido").each(function(index){
		$(this).click(function(){
			var isHidden = $(".cityLayout").eq(index).is(':hidden');
			$(".cityLayout").hide();
			
			if(isHidden)
				$(".cityLayout").eq(index).show();
			else
				$(".cityLayout").eq(index).hide();
		});
	});
*/
});

$(function(){
	// 시도 추가
	$(".btnSidoAdd").click(function(){
		var $text=$(this).parent().children("input:text");
		var sido=$text.val();
		if(! sido) {
			$text.focus();
			return;
		}
		
		var url="<%=cp%>/tour/insertSido";
		var query="sido="+encodeURIComponent(sido);
		
		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data){
				var state=data.state;
				if(state=="false") {
					alert("시도 중복등으로 추가하지 못했습니다.");
					return false;
				}
				
				var snum=data.snum;
				$text.val("");

				var s="<div class='sidoLayout' data-snum='"+snum+"'>";
				s+="      <div style='padding: 5px;'>";
				s+="         <span class='sido'>"+sido+"</span>&nbsp;";
				s+="         <span class='btnDelete btnSidoDelete'>삭제</span>";
				s+="      </div>";
				s+="      <div class='cityLayout'>";
				s+="         <div style='margin: 10px 3px;'>";
				s+="            <input type='text' class='boxTF' style='width:190px;'>&nbsp;";
				s+="            <button type='button' class='btn btnCityAdd'>도시추가</button>";
				s+="         </div>";
				s+="      <div class='listCity'></div>";
				s+="  </div>";
				
				$(".listSidoLayout").append(s);
			}
		    ,error:function(e) {
		    	console.log(e.responseText);
		    }
		});
	});
	
	// 시도 삭제
	$("body").on("click", ".btnSidoDelete", function(){
		if(! confirm("시도를 삭제하시겠습니까?")) {
			return false;
		}
		
		var $btn=$(this);
		
		var snum=$(this).closest(".sidoLayout").attr("data-snum");
		var url="<%=cp%>/tour/deleteSido";
		var query="snum="+snum;
		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data){
				if(data.state=="false") {
					alert("시도 삭제가 실패했습니다.");
					return false;
				}
				$btn.closest(".sidoLayout").remove();
			}
		    ,error:function(e) {
		    	console.log(e.responseText);
		    }
		});
	});
});

$(function(){
	// 도시 추가
	$("body").on("click", ".btnCityAdd", function(){
		var $listCity=$(this).closest(".cityLayout").children(".listCity");
		var $text=$(this).parent().children("input:text");
		
		var city=$text.val();
		var snum=$(this).closest(".sidoLayout").attr("data-snum");
		
		if(! city) {
			$text.focus();
			return false;
		}
		
		var url="<%=cp%>/tour/insertCity";
		var query="snum="+snum+"&city="+encodeURIComponent(city);

		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data){
				var state=data.state;
				if(state=="false") {
					alert("도시 중복등으로 추가하지 못했습니다.");
					return false;
				}
				var cnum=data.cnum;
				$text.val("");
				
				// 자식 노드 개수
				var cnt= $listCity.children().size();
				
				var out="";
				if(cnt==0)
					out+="<div class='city' style='border: 1px solid #cccccc; padding: 5px;'>";
				else
					out+="<div class='city' style='border: 1px solid #cccccc; padding: 5px; border-top:none;'>";
				out+="      <span class='cityName'>"+city+"</span>&nbsp;";
				out+="      <span class='btnDelete btnCityDelete' data-cnum='"+cnum+"'>삭제</span>";
				out+="    </div>";
				
				$listCity.append(out);
			}
		    ,error:function(e) {
		    	console.log(e.responseText);
		    }
		});
	});

	// 도시 삭제
	$("body").on("click", ".btnCityDelete", function(){
		if(! confirm("도시를 삭제하시겠습니까?")) {
			return false;
		}
		
		var $listCity=$(this).closest(".listCity");
		var $city=$(this).parent();
		
		var cnum=$(this).attr("data-cnum");
		
		var url="<%=cp%>/tour/deleteCity";
		var query="cnum="+cnum;
		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data){
				if(data.state=="false") {
					alert("도시 삭제가 실패했습니다.");
					return;
				}
				
				$city.remove();
				
				$listCity.children().first().css("border-top", "1px solid #cccccc");
			}
		    ,error:function(e) {
		    	console.log(e.responseText);
		    }
		});
	});
	
});

</script>
</head>
<body>

<div style="margin: 50px auto 10px; width:350px; min-height: 300px;">
    <div class="title">ㆍ시도 및 도시 관리</div>
    
    <div style="margin-top: 20px;">
         <input type="text" class="boxTF" style="width: 210px;">
         <button type="button" class="btn btnSidoAdd">시도추가</button>
    </div>
    
    <div class="listSidoLayout" style="margin-top: 20px;">
         <c:forEach var="dto" items="${list}">
              <div class="sidoLayout" data-snum="${dto.snum}">
                    <div style="padding: 5px;">
              		    <span class="sido">${dto.sido}</span>
              		    <span class="btnDelete btnSidoDelete">삭제</span>
              		 </div>
              		 <div class="cityLayout">
              		      <div style="margin: 10px 3px;">
                             <input type="text" class="boxTF" style='width:190px;'>
                             <button type="button" class="btn btnCityAdd">도시추가</button>
                         </div>
                         <div class="listCity"></div>
              		 </div>
              </div>  
         </c:forEach>
    </div>
    
    <div style="margin: 5px auto; width:100%;">
       <a href="<%=cp%>/tour/main" class="btn" style="text-decoration: none; display:inline-block; width: 94%;">돌아가기</a>
    </div>
    
</div>

</body>
</html>