<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.util.*, com.connection.*, com.guestbook.*"%>
<%
    //관리자 전용 페이지
    //세션 검사 과정
    Object sess = session.getAttribute("login");
    //String sess = (String)session.getAttribute("login");
    if (sess == null) {
        //강제 페이지 전환
        response.sendRedirect("guestbook.jsp");
    }
    
    
    
    String path = request.getContextPath();
    request.setCharacterEncoding("UTF-8");
    
    
    String result = request.getParameter("result");
    String message = "";
 
     
    if (result == null) {
        result = "";
    } else if (result.equals("success")) {
        message += "<div class='alert alert-success alert-dismissible'>";
        message += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
        message += "<strong>Success!</strong> 사진이 업로드 되었습니다.";
        message += "</div>";
    }
    
    AdminGuestbookDAO agdao = new AdminGuestbookDAO();
    List<Picture> list = agdao.plist();
    int row = 0;
    String picturelist = "";
    picturelist += "<div class='row'>";
    for (Picture p : list) {
        
        picturelist += "<div class='col-md-3'>";
        picturelist += "<div class='thumbnail'>";
        picturelist += "<img src='resources/picture/"+p.getPic_name_()+"' alt='piclist' style='width:100%'>";
        picturelist += "<div class='caption'>";
        picturelist += "<p>"+p.getContent_()+"</p>";
        picturelist += "</div></div></div>";
        ++row;
        if (row % 4 == 0) {
            picturelist += "</div><div class='row'>";
        }
 
    }
    if (row % 4 != 0) {
        picturelist += "</div>";
    }
%>
<!DOCTYPE html>
<html>
<head>
<title>쌍용교육센터</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 
<style>
h2 {
    text-align: center;
}
.search {
    display: inline-block;
}
</style>
 
<!-- jQuery library -->
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
<!-- Latest compiled JavaScript -->
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
<script>
    $(document).ready(function() {
 
    });
</script>
 
</head>
<body>
 
    <div class="container">
        <h2>
            <img src="img/1.PNG" width="100" height="70">방명록<small>v1.0</small>
        </h2>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">쌍용교육센터</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="adminguestbook.jsp">방명록관리</a></li>
                    <li class="active"><a href="#">사진관리</a></li>
                    <li><a href="logout.jsp">로그아웃</a></li>
                </ul>
            </div>
        </nav>
        <%=message%>
        <div class="panel panel-default" id="input">
            <div class="panel-heading">Picture Upload</div>
            <div class="panel-body">
                <!-- 
                    주의) 파일 업로드를 위해서 <form> 엘리먼트에
                    enctype="multipart/form-data" 속성 필수
                    -->
                <form action="fileupload.jsp" method="post"
                    enctype="multipart/form-data">
 
                    <div class="form-group">
                        <!-- 
                            주의) 파일 업로드를 위해서 파일선택폼 필요
                            type="file" 속성은 파일선택시 사용 
                            주의) 입력폼에 선택버튼이 기본 제공된다.
                            -->
                        <input type="file" class="form-control" id="filename"
                            name="filename" placeholder="파일선택" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="content"
                            name="content" placeholder="사진설명(max 100)" required value="">
                    </div>
 
                    <!-- 폼 전송시 type="submit" 속성 필수 -->
                    <button type="submit" class="btn btn-default">업로드</button>
 
                </form>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">사진 목록</div>
            <div class="panel-body">
             
                <%=picturelist%>
                
            </div>
        </div>
 
 
 
    </div>
 
</body>
</html>