<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="com.guestbook.*, java.util.*"%>
<%!%>
<%
    request.setCharacterEncoding("UTF-8");
    
    //페이지 수신
    String pagenum = request.getParameter("pagenum");
    String pagecount = request.getParameter("pagecount");
    if(pagenum == null){
        pagenum = "1";
    }
    
    if(pagecount == null){
        pagecount = "5";
    }
    GuestbookDAO gdao = new GuestbookDAO();
    List<Guestbook> list = gdao.glist(pagenum, pagecount);
    
    //최초 실행시 기본값 설정->1페이지\
    String predis = "";
    String nextdis = "";
    String prevpage = "" + (Integer.parseInt(pagenum) - 1);
    String nextpage = "" + (Integer.parseInt(pagenum) + 1);
    
    int tcnt = gdao.totalcount();
    int cnt = list.size();
    
    if(pagenum.equals("1")) {
        predis = "disabled='disabled'";
    }
    if(Integer.parseInt(pagenum) * Integer.parseInt(pagecount) >= tcnt) {
        nextdis = "disabled='disabled'";
    }
        
    //페이지 정보 제공
    //->offset, count 형태로 변환해서 제공
    //1페이지 -> 0, 10
    //2페이지 -> 10, 10
    //3페이지 -> 20, 10
 
 
    
    StringBuilder sb = new StringBuilder();
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
    }
    
    
 
    String result = request.getParameter("result");
    String message = "";
    
    if (result == null) {
        result = "";
    } else if (result.equals("success")) {
        message += "<div class='alert alert-success alert-dismissible'>";
        message += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
        message += "<strong>Success!</strong> 글작성에 성공하였습니다.";
        message += "</div>";
    } else if (result.equals("fail")) {
        message += "<div class='alert alert-danger alert-dismissible'>";
        message += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
        message += "<strong>Fail!</strong> 글 작성에 실패하였습니다.";
        message += "</div>";
    }
    
    String result1 = request.getParameter("result1");
    String message1 = "";
 
    if (result1 == null) {
        result1 = "";
    } else if (result1.equals("success")) {
        message1 += "<div class='alert alert-success alert-dismissible'>";
        message1 += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
        message1 += "<strong>Success!</strong> 삭제를 성공하였습니다";
        message1 += "</div>";
    } else if (result1.equals("fail")) {
        message1 += "<div class='alert alert-danger alert-dismissible'>";
        message1 += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
        message1 += "<strong>Fail!</strong> 삭제를 실패하였습니다.";
        message1 += "</div>";
    }
    
    String result2 = request.getParameter("result2");
    String message2 = "";
 
    if (result2 == null) {
        result2 = "";
    } else if (result2.equals("fail")) {
        message2 += "<div class='alert alert-danger alert-dismissible'>";
        message2 += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
        message2 += "<strong>Fail!</strong> 로그인에 실패하였습니다.";
        message2 += "</div>";
    } else if (result2.equals("logout")) {
        message2 += "<div class='alert alert-warning alert-dismissible'>";
        message2 += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
        message2 += "<strong>Logout!</strong> 로그아웃되었습니다.";
        message2 += "</div>";
    }
    
    
    
    
 
    AdminGuestbookDAO agdao = new AdminGuestbookDAO();
    List<Picture> list1 = agdao.plist();
    
    int row = 0;
    String cara = "";
    String sel = "";
    
    for (Picture p : list1) {
 
        if (row == 0) {
            cara += "<li data-target='#myCarousel' data-slide-to=" + row + " class='active'></li>";
            sel += "<div class='item active'>";
            sel += "<img src='resources/picture/" + p.getPic_name_() + "' alt='picture'>";
            sel += "</div>";
        } else {
            cara += "<li data-target='#myCarousel' data-slide-to=" + row + "></li>";
            sel += "<div class='item'>";
            sel += "<img src='resources/picture/" + p.getPic_name_() + "' alt='picture'>";
            sel += "</div>";
        }
        ++row;
 
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
        
        //이전, 다음 버튼에 대해서 value 속성의 값 할당
        //현재 페이지가 1인 경우 이전 버튼은 비활성, 다음 버튼은 2가 되도록 한다.
        //현재 페이지가 2인 경우 이전 버튼은 (현재페이지-1), 다음 버튼은 (현재페이지+1)가 되도록 한다.
        
        //이전, 다음 버튼에 대한 클릭 이벤트
        //->틀정 페이지 요청
        //->get 방식 요청
        $("#btnPrevious").on("click", function() {
            
            location.assign("guestbook.jsp?pagenum="+this.value+"&pagecount=5");
            
        });
        
        $("#btnNext").on("click", function() {
 
            location.assign("guestbook.jsp?pagenum="+this.value+"&pagecount=5");
            
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
            <div class="panel-body">
                서울 마포구 월드컵북로 21 2층 풍성빌딩 지번서울 마포구 서교동 447-5
                <button type="button" class="btn btn-default btn-xs"
                    data-toggle="modal" data-target="#myModal02">Map</button>
                <button type="button" class="btn btn-default btn-xs"
                    data-toggle="modal" data-target="#myModal03">Admin</button>
            </div>
        </div>
        <%=message2%>
        <div class="panel panel-default">
            <div class="panel-heading">Picture List</div>
            <div class="panel-body">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <%=cara %>
                    </ol>
 
                    <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                        <%=sel %>
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
                <div class="form-inline">
                    <!-- 검색 결과 자료 갯수 -->
                    <button type="button" class="btn btn-default">
                        Count <span class="badge" id="count"><%=cnt%></span>
                    </button>
 
                    <button type="button" class="btn btn-default btn-md" <%=predis %> id="btnPrevious" value=<%=prevpage%>>
                        Previous
                    </button>
                    <button type="button" class="btn btn-default btn-md" <%=nextdis %> id="btnNext" value=<%=nextpage %>>
                        Next 
                    </button>
                    <!-- 페이징 기준 선택 항목 -->
                    <select class="form-control" id="pageCount" name="pageCount">
                        <option value="5" selected="selected">5개씩보기</option>
                        <option value="10">10개씩보기</option>
                        <option value="15">15개씩보기</option>
                        <option value="20">20개씩보기</option>
                    </select>
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
                            <label for="ssn">게시물번호:</label> <input type="text"
                                class="form-control input-lg" id="ssn1" name="ssn" readonly>
                        </div>
                        <div class="form-group">
                            <label for="pwd">비밀번호:</label> <input type="password"
                                class="form-control input-lg" id="pwd" name="pw"
                                placeholder="password(max:20)">
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
 
    <!-- Modal -->
    <div class="modal fade" id="myModal02" role="dialog">
        <div class="modal-dialog">
 
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Google Map</h4>
                </div>
                <div class="modal-body">
                    <div class="mapsize">
                        <div id="googleMap" style="width: 100%; height: 400px;"></div>
                        <script>
                            function myMap() {
                                var mapProp = {
                                    center : new google.maps.LatLng(37.556458,
                                            126.921455),
                                    zoom : 17
                                };
                                var map = new google.maps.Map(document
                                        .getElementById("googleMap"), mapProp);
                                var marker = new google.maps.Marker({
                                    position : new google.maps.LatLng(
                                            37.556553, 126.919499)
                                });
                                marker.setMap(map);
                                var infowindow = new google.maps.InfoWindow({
                                    content : "강북쌍용교육센터<br>전화번호:02-1588-5588"
                                });
                                google.maps.event.addListener(marker, 'click',
                                        function() {
                                            infowindow.open(map, marker);
                                        });
                            }
                        </script>
 
                        <script
                            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVPywAdtG5UAexlrhBU8XYNsTTVdTSZWg&callback=myMap"></script>
                    </div>
 
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
 
        </div>
    </div>
 
    <div class="modal fade" id="myModal03" role="dialog">
        <div class="modal-dialog">
 
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Admin Login</h4>
                </div>
                <div class="modal-body">
                    <form action="login.jsp" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control input-lg" id="id_"
                                name="id_" placeholder="id(max:20)">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control input-lg" id="pw_"
                                name="pw_" placeholder="password(max:20)">
                        </div>
 
                        <button type="submit" class="btn btn-default">Login</button>
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
