<%@ page contentType="text/html; charset=UTF-8" %>
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
<title>Insert title here</title>
<style type="text/css">
*{
    margin:0; padding: 0;
}
body {
    font-size:14px;
	font-family:"Malgun Gothic", "맑은 고딕", NanumGothic, 나눔고딕, 돋움, sans-serif;
}
a{
    color:#000000;
    text-decoration:none;
    cursor:pointer;
}
a:active, a:hover {
    text-decoration: underline;
    color:tomato;
}
.title {
    font-weight:bold;
    font-size:16px;
    font-family:나눔고딕, "맑은 고딕", 돋움, sans-serif;
}
.btn {
    color:#333333;
    font-weight:500;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
    border:1px solid #cccccc;
    background-color:#ffffff;
    text-align:center;
    cursor:pointer;
    padding:3px 10px 5px;
    border-radius:4px;
}
.btn:active, .btn:focus, .btn:hover {
    background-color:#e6e6e6;
    border-color:#adadad;
    color:#333333;
}
.boxTF {
    border:1px solid #999999;
    padding:3px 5px 5px;
    border-radius:4px;
    background-color:#ffffff;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
}
.selectField {
    border:1px solid #999999;
    padding:2px 5px 4px;
    border-radius:4px;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
}

.title {
	width:100%;
	height:45px;
	line-height:45px;
	text-align:left;
	font-weight: bold;
	font-size:15px;
}

tr.over {
	background: #f5fffa;
}

.score-table input {
	border:none;
	padding:3px 5px 5px;
	background-color:#ffffff;
	width:100%;
	box-sizing:border-box;
	font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
}
.score-table input[readonly] {
    pointer-events: none;
    border: none;
    text-align: center;
}

.score-table button {
    color:#333333;
    font-weight:500;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
    border:none;
    width:100%;
    text-align:center;
    cursor:pointer;
    padding:4px 10px 4px;
    background-color: #ffffff;
}
.score-table button:hover {
    font-weight:500;
    color:#4374d9;
}

.spanUpdate, .spanUpdateOk, .spanDelete, .spanUpdateCancel {
	cursor: pointer;
}

.spanUpdate:hover, .spanUpdateOk:hover, .spanDelete:hover, .spanUpdateCancel:hover {
	font-weight:500;
	color: #4374d9;
}
</style>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
function isValidScoreFormat(data) {
    var regexp = /^(\d+)$/;
    if(! regexp.test(data))
        return false;
	var s = parseInt(data);
	return s>=0&&s<=100 ? true : false;
}

function isValidDateFormat(data){
    var regexp = /[12][0-9]{3}[\.|\-|\/]?[0-9]{2}[\.|\-|\/]?[0-9]{2}/;
    if(! regexp.test(data))
        return false;

    regexp=/(\.)|(\-)|(\/)/g;
    data=data.replace(regexp, "");
    
	var y=parseInt(data.substr(0, 4));
    var m=parseInt(data.substr(4, 2));
    if(m<1||m>12) 
    	return false;
    var d=parseInt(data.substr(6));
    var lastDay = (new Date(y, m, 0)).getDate();
    if(d<1||d>lastDay)
    	return false;
		
	return true;
}

// 엔터 처리
$(function(){
	$(document).on('keypress', '.score-table input:text', function(evt){
		var fields = $(this).closest("tr").find("input:text");
		var index = fields.index(this);
	        
		if (evt.keyCode == 13) {
			if ( index > -1 && index < 5 ) {
				fields.eq( index + 1 ).focus();
			} else {
				if($(this).closest("tr").find("#btnAdd").length>0) {
					$("#btnAdd").trigger("click");
				}
			}
			return false;
		}
	});
	   
	$(document).on('keyup', '.score-table input:text', function(evt){
		var fields = $(this).closest("tr").find("input:text");
		var index = fields.index(this);

		if(index>=3 && index<=5) {
			var kor=fields.eq(3).val().trim();
			var eng=fields.eq(4).val().trim();
			var mat=fields.eq(5).val().trim();

			var k = isValidScoreFormat(kor) ? parseInt(kor) : 0;
			var e = isValidScoreFormat(eng) ? parseInt(eng) : 0;
			var m = isValidScoreFormat(mat) ? parseInt(mat) : 0;
	        	
			var tot=k+e+m;
			var ave=Math.floor(tot/3);
	        	
			if(!kor && !eng && !mat) {
				tot="";
				ave="";
			}
	        	
			fields.eq(6).val(tot);
			fields.eq(7).val(ave);
		}
	});
});
 // 글 리스트
$(function(){
	listPage(1);
});

function listPage(page) {
	var url="<%=cp%>/score2/list";
	var query="pageNo="+page;
	
	// 리스트
	$.get(url, {pageNo:page}, function(data) {
		printScore(data);
	}, "json");
}

function printScore(data) {
	var dataCount=data.dataCount;
	var pageNo=data.pageNo;
	var paging=data.paging;
	var out="";
	if(dataCount!=0) {
		for(var idx=0; idx<data.list.length; idx++) {
			var hak=data.list[idx].hak;
			var name=data.list[idx].name;
			var birth=data.list[idx].birth;
			var kor=data.list[idx].kor;
			var eng=data.list[idx].eng;
			var mat=data.list[idx].mat;
			var tot=data.list[idx].tot;
			var ave=data.list[idx].ave;
			
			out+="<tr height='35' bgcolor='#E4E6E4' align='center'>";
			out+="<td width='80'>"+hak+"</td>";
			out+="<td width='100'>"+name+"</td>";
			out+="<td width='100'>"+birth+"</td>";
			out+="<td width='80'>"+kor+"</td>";
			out+="<td width='80'>"+eng+"</td>";
			out+="<td width='80'>"+mat+"</td>";
			out+="<td width='80'>"+tot+"</td>";
			out+="<td width='80'>"+ave+"</td>";
			out+="<td width='80'>";
			out+="<a class='spanUpdate'>수정</a>";
			out+=" | <a class='spanDelete'>삭제</a>";
			out+="</td>";
			out+="</tr>";
			
		}
		out+="<tr height='40'>";
		out+="  <td colspan='9' text-align='center'>";
		out+=paging;
		out+="  </td>";
		out+="</tr>";
		
	}
	
	$(".score-list").html(out);

}

// 등록하기
$(function(){
	$("#btnAdd").click(function(){
		if(! check()) {
			return false;
		}
		
		var f=document.scoreForm;
		f.hak.value=$("#hak").val().trim();
		f.name.value=$("#name").val().trim();
		f.birth.value=$("#birth").val().trim();
		f.kor.value=$("#kor").val().trim();
		f.eng.value=$("#eng").val().trim();
		f.mat.value=$("#mat").val().trim();
		
		// 등록하기
		var query=$("form[name=scoreForm]").serialize();
		var url="<%=cp%>/score2/insert";
		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data) {
				var state=data.state;
				if(state=="false") {
					alert("게시물 입력에 실패했습니다.");
				}
				$("#hak").val("");
				$("#name").val("");
				$("#birth").val("");
				$("#kor").val("");
				$("#eng").val("");
				$("#mat").val("");
				$("#tot").val("");
				$("#ave").val("");

				listPage(1);
			}
			,beforeSend:check
			,error:function(e) {
				console.log(e.responseText);
			}
		});
		
	});
	
	function check() {
		if(! $("#hak").val().trim()) {
			alert("학번을 입력 하세요 !!!");
			$("#hak").focus();
			return false;
		}
		
		if(! $("#name").val().trim()) {
			alert("이름을 입력 하세요 !!!");
			$("#name").focus();
			return false;
		}
		
		if(! isValidDateFormat($("#birth").val())) {
			alert("생년월일을 정확히 입력 하세요 !!!");
			$("#birth").focus();
			return false;
		}
		
		if(! isValidScoreFormat($("#kor").val()) ) {
			alert("점수는 숫자만 가능합니다. !!!");
			$("#kor").focus();
			return false;
		}
		
		if(! isValidScoreFormat($("#eng").val()) ) {
			alert("점수는 숫자만 가능합니다. !!!");
			$("#eng").focus();
			return false;
		}
		
		if(! isValidScoreFormat($("#mat").val()) ) {
			alert("점수는 숫자만 가능합니다. !!!");
			$("#mat").focus();
			return false;
		}

		return true;
	}
});
 // 수정
$(function(){
	var arr=[];
	// 수정 폼
	$("body").on("click", ".spanUpdate", function(){
		var tds = $(this).closest("tr").children("td");

		var s;
		
		$(tds).each(function(index){
			if(index!=tds.length-1) {
				arr[index]=$(this).text();
				s="";
				if(index==0||index==6||index==7)
					s="  readonly='readonly' "
				
				$(this).empty();
				$(this).append("<input type='text' value='"+arr[index]+"' "+s+">");
			} else {
				$(this).empty();
				$(this).append("<span class='spanUpdateOk'>완료</span>&nbsp;&nbsp;|&nbsp;&nbsp;<span class='spanUpdateCancel'>취소</span>");
			}
		});
		
		$(tds[1]).find("input").focus();
	});
	
	// 수정 완료
	$("body").on("click", ".spanUpdateOk", function(){
		var $inputs = $(this).closest("tr").find("input");
		var pageNo = $(this).closest("tr").attr("data-pageNo");

		if(! check($inputs)) return;
		
		$("form[name=scoreForm] input[type=hidden]").each(function(index){
			var $input = $(this);
			
			$input.val($inputs.eq(index).val());
		});
		
		var url="<%=cp%>/score2/update";
		var query=$("form[name=scoreForm]").serialize();
		
		// 수정완료
		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data) {
				var state=data.state;
				if(state=="false") {
					alert("게시물 수정를 실패했습니다.");
				}

				listPage(1);
			}
			,beforeSend:check
			,error:function(e) {
				console.log(e.responseText);
			}
		});
		
		
	});
	
	function check($inputs) {
		var returnValue=true;
		
		$($inputs).each(function(index){
			if(index==1 && ! $(this).val().trim()) {
				alert("이름을 입력 하세요 !!!");
				$(this).focus();
				returnValue=false;
				return false;
			}
			
			if(index==2 && ! isValidDateFormat($(this).val())) {
				alert("생년월일을 정확히 입력 하세요 !!!");
				$(this).focus();
				returnValue=false;
				return false;
			}
			
			if(index>=3 && index<=5 && ! isValidScoreFormat($(this).val())) {
				alert("점수를 정확히 입력 하세요 !!!");
				$(this).focus();
				returnValue=false;
				return false;
			}
			
		});
		
		return returnValue;
	}
	
	$("body").on("click", ".spanUpdateCancel", function(){
		var tds = $(this).closest("tr").children("td");
		$(tds).each(function(index){
			if(index!=tds.length-1) {
				$(this).empty();
				$(this).text(arr[index]);
			} else {
				$(this).empty();
				$(this).append("<span class='spanUpdate'>수정</span>&nbsp;&nbsp;|&nbsp;&nbsp;<span class='spanDelete' >삭제</span>");
			}
		});
	});
});

// 삭제
$(function(){
	$("body").on("click", ".spanDelete", function(){
		if(! confirm("데이터를 삭제 하시겠습니까 ? ")) {
			return false;
		}
		
		// 삭제
		var hak=$(this).closest("tr").children("td").first().text();
		var url="<%=cp%>/score2/delete";
		$.post(url, {hak:hak}, function(data) {
			var state=data.state;
			if(state=="false") {
				alert("게시물 삭제를 실패했습니다.");
			}
			listPage(1);
		}, "json");
		
		
	});
});
</script>

</head>
<body>

<div style="width: 800px; margin: 30px auto 0px;">

	<div style="title">
	   <h3><span>|</span> 성적 처리</h3>
	</div>

	<table class="score-table" style="width: 100%; margin: 20px auto 0px; border-spacing: 1px; background: #cccccc;">
	<thead>
		<tr height="35" bgcolor="#E4E6E4" align="center">
			<td width="80">학번</td>
			<td width="100">이름</td>
			<td width="100">생년월일</td>
			<td width="80">국어</td>
			<td width="80">영어</td>
			<td width="80">수학</td>
			<td width="80">총점</td>
			<td width="80">평균</td>
			<td>변경</td>
		</tr>
	</thead>
	
	<tbody class="score-input">
		<tr bgcolor="#ffffff" align="center">
			<td><input type="text" id="hak"></td>
			<td><input type="text" id="name"></td>
			<td><input type="text" id="birth"></td>
			<td><input type="text" id="kor"></td>
			<td><input type="text" id="eng"></td>
			<td><input type="text" id="mat"></td>
			<td><input type="text" id="tot" readonly="readonly" ></td>
			<td><input type="text" id="ave" readonly="readonly" ></td>
			<td>
				<button type="button" id="btnAdd">등록하기</button>
			</td>
		</tr>
	</tbody>
	
	<tfoot class="score-list">
	</tfoot>
	</table>
	
	<div class="pagingLayout" style="text-align: center; height: 50px;"></div>
	<form name="scoreForm" method="post">
		<input type="hidden" name="hak">
		<input type="hidden" name="name">
		<input type="hidden" name="birth">
		<input type="hidden" name="kor">
		<input type="hidden" name="eng">
		<input type="hidden" name="mat">
	</form>
	
</div>

</body>
</html>