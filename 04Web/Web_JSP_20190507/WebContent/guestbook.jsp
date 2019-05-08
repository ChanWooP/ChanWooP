<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="com.guestbook.*, java.util.*"%>
<%!%>
<%
    GuestbookDAO gdao = new GuestbookDAO();
 
    List<Guestbook> list = gdao.glist();
 
    StringBuilder sb = new StringBuilder();
    int tcnt = 0;
    int ssn=0;
    String result = request.getParameter("result");
    String message = "";
    
    String result1 = request.getParameter("result1");
    String message1 = "";
    
    if (result == null) {
        result = "";
    } else if (result.equals("success")) {
        message += "<div class='alert alert-success alert-dismissible'>";
        message += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
        message += "<strong>Success!</strong>글작성에 성공하였습니다.";
        message += "</div>";
    } else if (result.equals("fail")) {
        message += "<div class='alert alert-danger alert-dismissible'>";
        message += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
        message += "<strong>Fail!</strong>글 작성에 실패하였습니다.";
        message += "</div>";
    }
    
    if (result1 == null) {
        result1 = "";
    } else if (result1.equals("success")) {
        message1 += "<div class='alert alert-success alert-dismissible'>";
        message1 += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
        message1 += "<strong>Success!</strong>삭제를 성공하였습니다";
        message1 += "</div>";
    } else if (result1.equals("fail")) {
        message1 += "<div class='alert alert-danger alert-dismissible'>";
        message1 += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
        message1 += "<strong>Fail!</strong>삭제를 실패하였습니다.";
        message1 += "</div>";
    }
 
    //동적으로 <tr>, <td>를 작성하는 위치
    for (Guestbook g : list) {
        sb.append(String.format("<tr>"));
        sb.append(String.format("<td>%d</td>", g.getSsn()));
        sb.append(String.format("<td>%s</td>", g.getName_()));
        sb.append(String.format("<td>%s</td>", g.getContents()));
        sb.append(String.format("<td>%s</td>", g.getSdate()));
        sb.append(String.format("<td><button type='button' class='btn btn-default btnDelete' value="
                + g.getSsn() + ">삭제</button></td>"));
        sb.append(String.format("</tr>"));
        ++tcnt;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쌍용교육센터</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
 
<style>
h2 {
    text-align: center;
}
</style>
 
<!-- jQuery library -->
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
<!-- Latest compiled JavaScript -->
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
 
        $(".btnDelete").on("click", function() {
            console.log(this.value);
            $("#myModal01").children().find("#ssn1").val(this.value);
            $("#myModal01").modal();
            
        
        });
    });
 
    function myFunction() {
 
    }
</script>
 
</head>
<body>
    <div class="container">
        <!-- 방명록에 대한 정적 HTML 태그(화명 설계에 따른 구현) 구성 -->
        <h2>
            <img src="img/1.PNG" width="100" height="70">방명록<small>v1.0</small>
        </h2>
        <div class="panel panel-default">
            <div class="panel-body">서울 마포구 월드컵북로 21 2층 풍성빌딩 지번서울 마포구 서교동
                447-5</div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">Picture List</div>
            <div class="panel-body">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>
 
                    <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <img src="img/la.jpg" alt="Los Angeles">
                        </div>
 
                        <div class="item">
                            <img src="img/chicago.jpg" alt="Chicago">
                        </div>
 
                        <div class="item">
                            <img src="img/ny.jpg" alt="New York">
                        </div>
                    </div>
 
                    <!-- Left and right controls -->
                    <a class="left carousel-control" href="#myCarousel"
                        data-slide="prev"> <span
                        class="glyphicon glyphicon-chevron-left"></span> <span
                        class="sr-only">Previous</span>
                    </a> <a class="right carousel-control" href="#myCarousel"
                        data-slide="next"> <span
                        class="glyphicon glyphicon-chevron-right"></span> <span
                        class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">방명록 글쓰기</div>
            <div class="panel-body">
                <%=message%>
                <form action="guestbooki_insert.jsp" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control input-lg" id="name_"
                            name="name_" placeholder="name(max:20)">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control input-lg" id="pw"
                            name="pw" placeholder="password(max:20)">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control input-lg" id="contents"
                            name="contents" placeholder="content(max:500)">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
 
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">방명록 글목록</div>
            <div class="panel-body">
            <%=message1%>
                <table class="table">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>글쓴이</th>
                            <th>글내용</th>
                            <th>작성일</th>
                            <th>삭제</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%=sb.toString()%>
                    </tbody>
                </table>
                <div class="form-group row">
                    <div class="btncount col-xs-1">
                        <button type="button" class="btn btn-default">
                            TotalCount<span class="badge"><%=tcnt%></span>
                        </button>
                    </div>
                    <div class="col-xs-3">
                        <ul class="pager">
                            <li><a href="#">Previous</a></li>
                            <li><a href="#">Next</a></li>
                        </ul>
                    </div>
                    <div class="col-xs-2">
                        <select class="form-control" id="sel1">
                            <option>번호</option>
                            <option>글쓴이</option>
                            <option>글내용</option>
                        </select>
                    </div>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" placeholder="Search"
                            name="search">
                    </div>
                    <div class="col-xs-2">
                        <button class="btn btn-default" type="submit">
                            <i class="glyphicon glyphicon-search">Search</i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="myModal01" role="dialog">
        <div class="modal-dialog">
 
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">게시물 삭제</h4>
                </div>
                <div class="modal-body">
                    <form action="guestbook_delete.jsp" method="post">
                        <div class="form-group">
                        <label for="ssn">게시물번호:</label>
                            <input type="text" class="form-control input-lg" id="ssn1"
                                name="ssn">
                        </div>
                        <div class="form-group">
                        <label for="pwd">비밀번호:</label>
                            <input type="password" class="form-control input-lg" id="pwd"
                                name="pw" placeholder="password(max:20)">
                        </div>
 
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
 
        </div>
    </div>
</body>
</html>
