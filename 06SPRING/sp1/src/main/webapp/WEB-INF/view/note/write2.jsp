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
*{
    margin: 0px; padding: 0px;
}
body {
    font-size: 14px;
    font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
    box-sizing: border-box;  /* padding과 border는 크기에 포함되지 않음 */    
}
a{
    color: #000000;
    text-decoration: none;
    cursor: pointer;
}
a:active, a:hover {
    text-decoration: underline;
    color: tomato;
}
.btn {
    color:#333333;
    font-weight:500;
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
    padding:4px 5px 5px;
    border-radius:4px;
    background-color:#ffffff;
}
.selectField {
    border:1px solid #999999;
    padding:2px 5px 6px;
    border-radius:4px;
    font-size: 12px;
}
.boxTA {
    border:1px solid #999999;
    height:150px;
    padding:3px 5px;
    border-radius:4px;
    background-color:#ffffff;
}
.title {
    width:100%;
    height:45px;
    line-height:45px;
    text-align:left;
    font-weight: bold;
    font-size:15px;
}
</style>

<script type="text/javascript">
    function itemAdd() {
        var f = document.noteForm;
        var friends=f.friends;
        
        // friends[0]=new Option('가가가','ka'); // text, value
        // friends[1]=new Option('나나나','na');
        var idx;
        <c:forEach var="dto" items="${friendList}" varStatus="status">
        	idx=${status.index};
			friends[idx]=new Option('${dto.userName}','${dto.userId}');
        </c:forEach>
    }

    function itemMove(ob) {
    	// 친구목록 <-> 리시피언트
        var f = document.noteForm;
        var source, target;
        if(ob == "left") {
            source = f.recipient;
            target = f.friends;
        } else {
            target = f.recipient;
            source = f.friends;
        }

/*
        var  n=target.length;
        for(var i = source.length-1; i>=0; i--) {
            if(source.options[i].selected) {
                target[n++]=new Option(source.options[i].text, source.options[i].value);
                source[i] = null;
            }
        }
*/ 
        var  n=target.length;
        var len=source.length;
        for(var i = 0; i<len; i++) {
            if(source.options[i].selected) {
                target[n++]=new Option(source.options[i].text, source.options[i].value);
                source[i] = null;
                i--;
                len--;
            }
        }
    }
    
    function itemAllMove(ob) {
        var f = document.noteForm;
        var source, target;
        if(ob == "left") {
            source = f.recipient;
            target = f.friends;
        } else {
            target = f.recipient;
            source = f.friends;
        }

        var n = target.length;
        var len=source.length;
        for(var i = 0; i<len; i++) {
               target[n++]=new Option(source.options[0].text, source.options[0].value);
               source[0] = null;
        }
    }

    function sendOk() {
        var f = document.noteForm;

        if(f.recipient.length == 0) {
            alert("메시지를 받는 사람을 먼저 추가 하세요 !!!");
            return false; 
        }

        if(!f.msg.value) {
            alert("메시지를 입력 하세요 !!!");
            return false; 
        }

        for(i=0; i<f.recipient.length; i++) {
            f.recipient[i].selected=true;
        }

        f.action="<%=cp%>/note/request";
        f.submit();
    }
    
    window.onload = function(){
    	itemAdd();
    }
</script>
</head>
<body>

<div style="width: 400px; margin: 30px auto 0px;">
    <div style="title">
       <h3><span>|</span> 쪽지 보내기</h3>
    </div>

    <form name="noteForm" method="post">
    <table style="width: 100%; margin: 10px auto 0px;">
    <tr height="25">
        <td style="width: 35%;"><span>친구목록</span></td>
        <td style="width: 30%;">&nbsp;</td>
        <td style="width: 35%;"><span>받는사람</span></td>
    </tr>
    
    <tr>
        <td align="center" style="padding: 3px;">
            <select name="friends" multiple="multiple" class="selectField" style="width:130px; height:120px;"></select>
        </td>
        <td align="center">
            <button type="button" class="btn" onclick="itemMove('right');" style="display:block; width:80px;"> &gt; </button>
            <button type="button" class="btn" onclick="itemAllMove('right');" style="display:block;width:80px;"> &gt;&gt; </button>
            <button type="button" class="btn" onclick="itemMove('left');" style="display:block;width:80px;"> &lt; </button>
            <button type="button" class="btn" onclick="itemAllMove('left');" style="display:block;width:80px;"> &lt;&lt; </button>
        </td>
        <td align="center" style="padding: 3px;">
            <select name="recipient" multiple="multiple" class="selectField" style="width:130px; height:120px;">
            </select>
        </td>
    </tr>
    <tr height="25">
        <td colspan="3" align="left" style="padding-top: 5px;">
           <span>메시지</span>
        </td>
    </tr>
    <tr>
        <td colspan="3" style="padding: 3px;">
            <textarea rows="5" cols="60" name="msg" class="boxTA" style="height:60px; width: 95%;"></textarea>
        </td>
    </tr>
    </table>
    
    <table style="width: 100%; margin: 0px auto 0px;">
    <tr height="30">
        <td align="right" style="padding-right: 4%;">
            <button type="button" class="btn" onclick="sendOk();"> 쪽지보내기 </button>
        </td>
    </tr>
    </table>
    </form> 
</div>

</body>
</html>