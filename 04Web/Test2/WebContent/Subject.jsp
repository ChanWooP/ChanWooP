<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="com.subject.*, java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	SubjectDAO sDAO = new SubjectDAO();

	List<Subject_> list = sDAO.slist();

	StringBuilder sb = new StringBuilder();

	for (Subject_ s : list) {
		sb.append(String.format("<tr>"));
		sb.append(String.format("<td>%s</td>", s.getSubId()));
		sb.append(String.format("<td>%s</td>", s.getSubName()));
		sb.append(String.format("<td>%s</td>", s.getSubDate_()));
		sb.append(String.format("<td>%s</td>", s.getSubNum()));
		sb.append(String.format("<td>%d</td>", s.getSubCount()));
		sb.append(String.format("<td><button type='button' class='btn btn-default btnUpdate' value="
				+ s.getSubId() + ">수정</button></td>"));
		sb.append(String.format("<td><button type='button' class='btn btn-default btnDelete' value="
				+ s.getSubId() + ">삭제</button></td>"));
		sb.append(String.format("</tr>"));
	}

	String resultI = request.getParameter("resultI");
	String messageI = "";

	if (resultI == null) {
		resultI = "";
	} else if (resultI.equals("1")) {
		messageI += "<div class='alert alert-success alert-dismissible'>";
		messageI += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
		messageI += "<strong>Success!</strong> 글작성에 성공하였습니다.";
		messageI += "</div>";
	} else if (resultI.equals("0")) {
		messageI += "<div class='alert alert-danger alert-dismissible'>";
		messageI += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
		messageI += "<strong>Fail!</strong> 글 작성에 실패하였습니다.";
		messageI += "</div>";
	}

	String resultD = request.getParameter("resultD");
	String messageD = "";

	if (resultD == null) {
		resultD = "";
	} else if (resultD.equals("1")) {
		messageD += "<div class='alert alert-success alert-dismissible'>";
		messageD += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
		messageD += "<strong>Success!</strong> 글작성에 성공하였습니다.";
		messageD += "</div>";
	} else if (resultD.equals("0")) {
		messageD += "<div class='alert alert-danger alert-dismissible'>";
		messageD += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
		messageD += "<strong>Fail!</strong> 글 작성에 실패하였습니다.";
		messageD += "</div>";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<style>
#title {
	text-align: center;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script>
	$(document).ready(
			function() {
				$(".btnDelete").on(
						"click",
						function() {
							$("#Modal_Delete").children().find("#SubId_Delete")
									.val(this.value);
							$("#Modal_Delete").modal();

						});
				$(".btnUpdate").on(
						"click",
						function() {
							var p = $(this).parent().parent().children();
							$("#subId").val(p[0].innerText)
							$("#subName").val(p[1].innerText)
							$("#subDate_").val(p[2].innerText)
							$("#subNum").val(p[3].innerText)
						});
			});
	
	function myFunction() {
	}
</script>
</head>
<body>
	<div class="container">
		<h2 id="title">
			<img src='img/1.PNG'>시험 v1.0
		</h2>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">과목관리</a></li>
					<li><a href="#">문제관리</a></li>
					<li><a href="#">채점관리</a></li>
					<li><a href="#">[관리자/admin]로그아웃</a></li>
				</ul>
			</div>
		</nav>
		<div class="panel panel-default">
			<div class="panel-heading">Subject Upload</div>
			<div class="panel-body">
				<%=messageI%>
				<form action="Subject_Insert.jsp" method="post">
					<div class="form-group">
						<label for="subId">과목번호:</label> <input type="text"
							class="form-control" id="subId" name="subId">
					</div>
					<div class="form-group">
						<label for="subName">과목이름:</label> <input type="text"
							class="form-control" id="subName" name="subName">
					</div>
					<div class="form-group">
						<label for="subDate_">과목기간:</label> <input type="text"
							class="form-control" id="subDate_" name="subDate_">
					</div>
					<div class="form-group">
						<div class="form-group">
							<label for="subNum">차수:</label> <select class="form-control"
								id="subNum" name="subNum">
								<option>1차</option>
								<option>2차</option>
								<option>3차</option>
								<option>4차</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">등록</button>
					</div>
				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">Subject List</div>
			<div class="panel-body">
				<%=messageD%>
				<table class="table">
					<thead>
						<tr>
							<th>과목번호</th>
							<th>과목명</th>
							<th>기간</th>
							<th>차수</th>
							<th>시험문제갯수</th>
							<th>수정</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<%=sb.toString()%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div id="Modal_Delete" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<form action="Subject_Delete.jsp" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">과목삭제</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="SubId_Delete">과목번호:</label> <input type="text"
								class="form-control input-lg" id="SubId_Delete"
								name="SubId_Delete" readonly>
						</div>
						<div class="form-group" align="right">
							<button type="submit" class="btn btn-default">삭제하기</button>
						</div>
					</div>
					<div class="modal-footer">

						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>