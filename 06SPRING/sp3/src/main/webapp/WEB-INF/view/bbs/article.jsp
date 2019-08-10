<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>

<script type="text/javascript">
function deleteBoard() {
<c:if test="${sessionScope.member.userId=='admin' || sessionScope.member.userId==dto.userId}">
	var q = "num=${dto.num}&${query}";
	var url = "<%=cp%>/bbs/delete?" + q;

	if(confirm("위 자료를 삭제 하시 겠습니까 ? ")) {
			location.href=url;
	}
</c:if>    
<c:if test="${sessionScope.member.userId!='admin' && sessionScope.member.userId!=dto.userId}">
	alert("게시물을 삭제할 수  없습니다.");
</c:if>
}

function updateBoard() {
<c:if test="${sessionScope.member.userId==dto.userId}">
	var q = "num=${dto.num}&page=${page}";
	var url = "<%=cp%>/bbs/update?" + q;

	location.href=url;
</c:if>

<c:if test="${sessionScope.member.userId!=dto.userId}">
	alert("게시물을 수정할 수  없습니다.");
</c:if>
}
</script>

<script type="text/javascript">
$(function(){
	$(".btnSendBoardLike").click(function(){
		var url="<%=cp%>/bbs/insertBoardLike";
		var num="${dto.num}";
		var query="num="+num;
		
		$.ajax({
			type:"post"
			,url:url
			//,data:{num:num}
			,data:query
			,dataType:"json"
			,success:function(data) {
				var state=data.state;
				if(state=="true") {
					var count=data.boardLikeCount;
					$("#boardLikeCount").text(count);
				}else if(state=="false") {
					alert("좋아요는 한번만 가능합니다. !!!");
				}
			}
			,beforeSend:function(e) {
				e.setRequestHeader("AJAX", true); // 헤더에 AJAX라는 이름으로 true를 전송
			}
			,error:function(e) {
				if(e.status==403) {
					location.href="<%=cp%>/member/login";
					return false;
				}
		    	console.log(e.responseText);
		    }			
		});
		
	});
});

function listPage(page) {
	var url="<%=cp%>/bbs/listReply";
	var query="num=${dto.num}&pageNo="+page;
	
	$.ajax({
		type:"get"
		,url:url
		,data:query
		,success:function(data) {
			$("#listReply").html(data);
		}
		,beforeSend:function(e) {
			e.setRequestHeader("AJAX", true);
		}
		,error:function(e) {
			if(e.status==403) {
				location.href="<%=cp%>/member/login";
				return false;
			}
	    	console.log(e.responseText);
	    }
	});
}

$(function(){
	listPage(1);
});


$(function(){
	// 댓글 등록
	$(".btnSendReply").click(function(){
		var num="${dto.num}";
		var $tb=$(this).closest("table");
		var content=$tb.find("textarea").val().trim();
		if(! content) {
			$tb.find("textarea").focus();
			return false;
		}
		content=encodeURIComponent(content);
		var answer=0;
		
		var query="num="+num+"&content="+content+"&answer="+answer;
		var url="<%=cp%>/bbs/insertReply";
		
		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data) {
				var state=data.state;
				
				$tb.find("textarea").val("");
				if(state=="true") {
					listPage(1);
			}
			,beforeSend:function(e) {
				e.setRequestHeader("AJAX", true);
			}
			,error:function(e) {
				if(e.status==403) {
					location.href="<%=cp%>/member/login";
					return false;
				}
		    	console.log(e.responseText);
		    }	
		});
	});
	
	// 댓글 삭제
	$("body").on("click", ".deleteReply", function(){
		if(! confirm("게시물을 삭제 하시겠습니까 ?")) {
			return;
		}
		
		var replyNum=$(this).attr("data-replyNum");
		var page=$(this).attr("data-pageNo");
		
		var url="<%=cp%>/bbs/deleteReply";
		var query="replyNum="+replyNum+"&mode=reply";
		
		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data) {
				var state=data.state;
				
				listPage(page);
			}
			,beforeSend:function(e) {
				e.setRequestHeader("AJAX", true);
			}
			,error:function(e) {
				if(e.status==403) {
					location.href="<%=cp%>/member/login";
					return false;
				}
		    	console.log(e.responseText);
		    }
		});
		
	});
	
});

// 댓글별 답글 리스트
function listReplyAnswer(answer) {
	var url="<%=cp%>/bbs/listReplyAnswer";
	var query="answer="+answer;
	
	$.ajax({
		type:"get"
		,url:url
		,data:query
		,success:function(data) {
			var id="#listReplyAnswer"+answer;
			$(id).html(data);
		}
		,beforeSend:function(e) {
			e.setRequestHeader("AJAX", true);
		}
		,error:function(e) {
			if(e.status==403) {
				location.href="<%=cp%>/member/login";
				return false;
			}
	    	console.log(e.responseText);
	    }
	});
}

// 댓글별 답글 개수
function countReplyAnswer(answer) {
	var url="<%=cp%>/bbs/countReplyAnswer";
	var query="answer="+answer;
	
	$.ajax({
		type:"post"
		,url:url
		,data:query
		,dataType:"json"
		,success:function(data) {
			var count=data.count;
			var id="#answerCount"+answer;
			$(id).html(count);
		}
		,beforeSend:function(e) {
			e.setRequestHeader("AJAX", true);
		}
		,error:function(e) {
			if(e.status==403) {
				location.href="<%=cp%>/member/login";
				return false;
			}
	    	console.log(e.responseText);
	    }
	});
}

$(function(){
	// 답글 버튼
	$("body").on("click", ".btnReplyAnswerLayout", function(){
		var $tr=$(this).closest("tr").next();
		var isVisible=$tr.is(":visible");
		var replyNum = $(this).attr("data-replyNum");
		
		if(isVisible) {
			$tr.hide();
		} else {
			$tr.show();
			
			listReplyAnswer(replyNum);  // 댓글의 답글 리스트
			countReplyAnswer(replyNum); // 댓글의 답글 개수
		}
	});
	
	// 댓글별 답글 등록 버튼
	$("body").on("click", ".btnSendReplyAnswer", function(){
		var num="${dto.num}";
		var replyNum=$(this).attr("data-replyNum");
		var $td=$(this).closest("td");
		var content=$td.find("textarea").val().trim();
		if(! content) {
			$td.find("textarea").focus();
			return false;
		}
		
		content=encodeURIComponent(content);
		var answer=replyNum;
		
		var q="num="+num+"&content="+content+"&answer="+answer;
		var url="<%=cp%>/bbs/insertReply";
		
		$.ajax({
			type:"post"
			,url:url
			,data:q
			,dataType:"json"
			,success:function(data) {
				var state=data.state;
				
				$td.find("textarea").val("");
				if(state=="true") {
					listReplyAnswer(replyNum);  // 댓글의 답글 리스트
					countReplyAnswer(replyNum); // 댓글의 답글 개수
				}
			}
			,beforeSend:function(e) {
				e.setRequestHeader("AJAX", true);
			}
			,error:function(e) {
				if(e.status==403) {
					location.href="<%=cp%>/member/login";
					return false;
				}
		    	console.log(e.responseText);
		    }		
		});
		
	});
	
	// 댓글의 답글 삭제
	$("body").on("click", ".deleteReplyAnswer", function(){
		if(! confirm("게시물을 삭제 하시겠습니까 ?")) {
			return;
		}
		
		var replyNum=$(this).attr("data-replyNum");
		var answer=$(this).attr("data-answer");
		
		var url="<%=cp%>/bbs/deleteReply";
		var query="replyNum="+replyNum+"&mode=answer";
		
		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data) {
				var state=data.state;
				
				listReplyAnswer(answer);
				countReplyAnswer(answer);
			}
			,beforeSend:function(e) {
				e.setRequestHeader("AJAX", true);
			}
			,error:function(e) {
				if(e.status==403) {
					location.href="<%=cp%>/member/login";
					return false;
				}
		    	console.log(e.responseText);
		    }
		});
	});
	
});

$(function(){
	// 댓글 좋아요/싫어요 등록
	$("body").on("click", ".btnSendReplyLike", function(){
		var replyNum=$(this).attr("data-replyNum");
		var replyLike=$(this).attr("data-replyLike");
		var $btn=$(this);
		
		var msg="게시글이 마음에 들지 않으십니까 ? ";
		if(replyLike==1) msg="게시글에 공감하십니까 ? ";
		
		if(! confirm(msg)) {
			return false;
		}
		
		var url="<%=cp%>/bbs/insertReplyLike";
		var query="replyNum="+replyNum+"&replyLike="+replyLike;
		$.ajax({
			type:"post"
			,url:url
			,data:query
			,dataType:"json"
			,success:function(data) {
				var state=data.state;
				
				if(state=="true") {
					var likeCount=data.likeCount;
					var disLikeCount=data.disLikeCount;
					
					$btn.parent("td").children().eq(0).find("span").html(likeCount);
					$btn.parent("td").children().eq(1).find("span").html(disLikeCount);
					
				} else if(state=="false") {
					alert("게시글 공감 여부는 한번만 가능합니다.");
				}
			}
			,beforeSend:function(e) {
				e.setRequestHeader("AJAX", true);
			}
			,error:function(e) {
				if(e.status==403) {
					location.href="<%=cp%>/member/login";
					return false;
				}
		    	console.log(e.responseText);
		    }
		});
		
	});
});

</script>

<div class="body-container" style="width: 700px;">
    <div class="body-title">
        <h3><span style="font-family: Webdings">2</span> 게시판 </h3>
    </div>
    
    <div>
			<table style="width: 100%; margin: 20px auto 0px; border-spacing: 0px; border-collapse: collapse;">
			<tr height="35" style="border-top: 1px solid #cccccc; border-bottom: 1px solid #cccccc;">
			    <td colspan="2" align="center">
				   ${dto.subject}
			    </td>
			</tr>
			
			<tr height="35" style="border-bottom: 1px solid #cccccc;">
			    <td width="50%" align="left" style="padding-left: 5px;">
			       이름 : ${dto.userName}
			    </td>
			    <td width="50%" align="right" style="padding-right: 5px;">
			        ${dto.created} | 조회 ${dto.hitCount}
			    </td>
			</tr>
			
			<tr>
			  <td colspan="2" align="left" style="padding: 10px 5px;" valign="top" height="200">
			      ${dto.content}
			   </td>
			</tr>
			
			<tr style="border-bottom: 1px solid #cccccc;">
				<td colspan="2" height="40" style="padding-bottom: 15px;" align="center">
					<button type="button" class="btn btnSendBoardLike"><span style="font-family: Wingdings;">C</span>&nbsp;&nbsp;<span id="boardLikeCount">${dto.boardLikeCount}</span></button>
				</td>
			</tr>
			
			<tr height="35" style="border-bottom: 1px solid #cccccc;">
			    <td colspan="2" align="left" style="padding-left: 5px;">
			       첨&nbsp;&nbsp;부 :
		           <c:if test="${not empty dto.saveFilename}">
		                   <a href="<%=cp%>/bbs/download?num=${dto.num}">${dto.originalFilename}</a>
		           </c:if>
			    </td>
			</tr>
			
			<tr height="35" style="border-bottom: 1px solid #cccccc;">
			    <td colspan="2" align="left" style="padding-left: 5px;">
			       이전글 :
			         <c:if test="${not empty preReadDto}">
			              <a href="<%=cp%>/bbs/article?${query}&num=${preReadDto.num}">${preReadDto.subject}</a>
			        </c:if>
			    </td>
			</tr>
			
			<tr height="35" style="border-bottom: 1px solid #cccccc;">
			    <td colspan="2" align="left" style="padding-left: 5px;">
			       다음글 :
			         <c:if test="${not empty nextReadDto}">
			              <a href="<%=cp%>/bbs/article?${query}&num=${nextReadDto.num}">${nextReadDto.subject}</a>
			        </c:if>
			    </td>
			</tr>
			</table>
			
			<table style="width: 100%; margin: 0px auto 20px; border-spacing: 0px;">
			<tr height="45">
			    <td width="300" align="left">
			       <c:if test="${sessionScope.member.userId==dto.userId}">				    
			          <button type="button" class="btn" onclick="updateBoard();">수정</button>
			       </c:if>
			       <c:if test="${sessionScope.member.userId==dto.userId || sessionScope.member.userId=='admin'}">				    
			          <button type="button" class="btn" onclick="deleteBoard();">삭제</button>
			       </c:if>
			    </td>
			
			    <td align="right">
			        <button type="button" class="btn" onclick="javascript:location.href='<%=cp%>/bbs/list?${query}';">리스트</button>
			    </td>
			</tr>
			</table>
    </div>
    
    <div>
		<table style='width: 100%; margin: 15px auto 0px; border-spacing: 0px;'>
			<tr height='30'> 
				 <td align='left' >
				 	<span style='font-weight: bold;' >댓글쓰기</span><span> - 타인을 비방하거나 개인정보를 유출하는 글의 게시를 삼가 주세요.</span>
				 </td>
			</tr>
			<tr>
			   	<td style='padding:5px 5px 0px;'>
					<textarea class='boxTA' style='width:99%; height: 70px;'></textarea>
			    </td>
			</tr>
			<tr>
			   <td align='right'>
			        <button type='button' class='btn btnSendReply' data-num='10' style='padding:10px 20px;'>댓글 등록</button>
			    </td>
			 </tr>
		</table>
		     
		<div id="listReply"></div>
    
    </div>
    
</div>
