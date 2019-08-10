<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>

<style type="text/css">
#chatMsgContainer{
   clear:both;
   border: 1px solid #ccc;
   height: 285px;
   overflow-y: scroll;
   padding: 3px;
   width: 100%;
}
#chatMsgContainer p{
   padding-bottom: 0px;
   margin-bottom: 0px;
}
#chatConnectionList{
	clear:both;
	width: 100%;
	height: 315px;
	text-align:left;
	padding:5px 5px 5px 5px;
	overflow-y:scroll;
    border: 1px solid #ccc;
}
</style>

<script src="http://localhost:3001/socket.io/socket.io.js"></script>
<script type="text/javascript">
	$(function(){
		var uid = "${sessionScope.member.userId}";
		var nickName = "${sessionScope.member.userName}";
		
		var chat = io("http://localhost:3001/chat");
		
		chat.emit("connUser", {userId:uid, nickName:nickName});
		
		//접속자 리스트
		chat.on("connList", function(data){
			var userId, nickName, s;
			
			for(var idx=0; idx<data.length; idx++){
				userId = data[idx].userId;
				nickName = data[idx].nickName;
				
				s="<span style='display:block;' id='guest-"+userId+"'>"+nickName+"("+userId+")"+"</span>";
				$("#chatConnectionList").append(s);
			}
		});
		
		chat.on("connUser", function(data){
			var userId = data.userId;
			var nickName = data.nickName;
			
			var s;
			if(userId==uid){
				s="채팅방에 입장했습니다.";
			} else {
				s="<span style='display:block;' id='guest-"+userId+"'>"+nickName+"("+userId+")"+"</span>";
				$("#chatConnectionList").append(s);
				
				s = nickName + "님이 입장했습니다.";
			}
			writeToViewer(s);
		});
		
		chat.on("chatMsg", function(data){
			var userId = data.userOd;
			var nickName = data.nickName;
			var msg = data.msg;
			
			var s;
			if(userId == uid){
				s = "보냄] "+msg;
			} else {
				s = nickName + "] "+msg;
			}
			writeToViewer(s);
		});
		
		chat.on("disUser", function(data){
			var userId = data.userId;
			var nickName = data.nickName;
			
			var s = nickName+"님이 퇴장했습니다.";
			$("#guest-"+userId).remove();
			
			writeToViewer(s);
		});
		
		$("#chatMsg").on("keydown", function(event){
			if(event.keyCode==13){
				var msg = $('#chatMsg').val().trim();
				
				if(!msg) return false;
				
				chat.emit("chatMsg",{
					userId:uid,
					nickName:nickName,
					msg:msg
				});
				
				$("#chatMsg").val("");
				$("#chatMsg").focus();
			}
		});
		
		//메시지 출력
		function writeToViewer(message) {
			var $cc = $("#chatMsgContainer");
			$cc.append("<p>");
			$cc.find("p:last").css("wordWrap", "break-work");
			$cc.find("p:last").html(message);
			
			$cc.scrollTop($cc.prop("scrollHeight"));
			
		}
	});
</script>

<div class="body-container" style="width: 700px;">
    <div class="body-title">
        <h3><i class="far fa-comment-alt"></i> 채팅 <small style="font-size:65%; font-weight: normal;">Chatting</small></h3>
    </div>
    
    <div style="clear: both;">
        <div style="float: left; width: 350px;">
            <div style="clear: both; padding-bottom: 5px;">
                <span style="font-weight: 600;">＞</span>
                <span style="font-weight: 600; font-family: 나눔고딕, 맑은 고딕, 돋움; color: #424951;">채팅 메시지</span>
            </div>
            <div id="chatMsgContainer"></div>
            <div style="clear: both; padding-top: 5px;">
                <input type="text" id="chatMsg" class="boxTF"  style="width: 99%;"
                            placeholder="채팅 메시지를 입력 하세요...">
            </div>
        </div>
        
        <div style="float: left; width: 20px;">&nbsp;</div>
        
        <div style="float: left; width: 170px;">
            <div style="clear: both; padding-bottom: 5px;">
                <span style="font-weight: 600;">＞</span>
                <span style="font-weight: 600; font-family: 나눔고딕, 맑은 고딕, 돋움; color: #424951;">접속자 리스트</span>
            </div>
            <div id="chatConnectionList"></div>
        </div>
    </div>
</div>