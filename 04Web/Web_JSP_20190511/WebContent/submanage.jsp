<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="com.exam.*, java.util.*"%>
<%!%>
<%
	String path = request.getContextPath();
	request.setCharacterEncoding("UTF-8");

	String result = request.getParameter("result");
	String message = "";

	if (result == null) {
		result = "";
	} else if (result.equals("success")) {
		message += "<div class='alert alert-success alert-dismissible'>";
		message += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
		message += "<strong>Success!</strong> 시험이 추가되었습니다.";
		message += "</div>";
	} else if (result.equals("fail")) {
		message += "<div class='alert alert-danger alert-dismissible'>";
		message += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
		message += "<strong>Fail!</strong> 시험 등록에 실패하였습니다";
		message += "</div>";
	}

	String result1 = request.getParameter("result1");
	String message1 = "";

	if (result1 == null) {
		result1 = "";
	} else if (result1.equals("success")) {
		message1 += "<div class='alert alert-success alert-dismissible'>";
		message1 += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
		message1 += "<strong>Success!</strong> 시험이 삭제되었습니다.";
		message1 += "</div>";
	} else if (result1.equals("fail")) {
		message1 += "<div class='alert alert-danger alert-dismissible'>";
		message1 += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
		message1 += "<strong>Fail!</strong> 시험 삭제에 실패하였습니다";
		message1 += "</div>";
	}
	
	String result2 = request.getParameter("result2");
	String message2 = "";

	if (result2 == null) {
		result2 = "";
	} else if (result2.equals("success")) {
		message2 += "<div class='alert alert-success alert-dismissible'>";
		message2 += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
		message2 += "<strong>Success!</strong> 시험정보가 수정되었습니다";
		message2 += "</div>";
	} else if (result2.equals("fail")) {
		message2 += "<div class='alert alert-danger alert-dismissible'>";
		message2 += "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
		message2 += "<strong>Fail!</strong> 시험정보 수정에 실패하였습니다";
		message2 += "</div>";
	}

	SubjectinfoDAO sidao = new SubjectinfoDAO();
	List<Subjectinfo> list = sidao.subjectList();

	StringBuilder sb = new StringBuilder();

	for (Subjectinfo si : list) {
		sb.append(String.format("<tr>"));
		sb.append(String.format("<td class='subid'>%s</td>", si.getSubjectid_()));
		sb.append(String.format("<td class='sname'>%s</td>", si.getSname_()));
		sb.append(String.format("<td class='sdate'>%s</td>", si.getSdate_()));
		sb.append(String.format("<td class='scount'>%d</td>", si.getScount_()));
		sb.append(String.format("<td>%d</td>", si.getExamcount_()));
		sb.append(String.format("<td><button type='button' class='btn btn-default btnUpdate' value="
				+ si.getSid_() + ">수정</button></td>"));
		sb.append(String.format("<td><button type='button' class='btn btn-default btnDelete' value="
				+ si.getSid_() + ">삭제</button></td>"));
		sb.append(String.format("</tr>"));
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

			var subid = $(this).parents().siblings('.subid').text();
			var scount = $(this).parents().siblings('.scount').text();
			sublistDelete(this.value, subid, scount);

		});

		$(".btnUpdate").on("click", function() {

			$("#btnSublist").text("수정");
			$("#form_").attr("action", "sublist_update.jsp?sid=" + this.value);
			
			var subid = $(this).parents().siblings('.subid').text();
			var sname = $(this).parents().siblings('.sname').text();
			var sdate = $(this).parents().siblings('.sdate').text();

			$("#subjectid_").val(subid);
			$("#sname_").val(sname);
			$("#sdate_").val(sdate);
			

		});

	});

	function sublistDelete(value, subid, scount) {

		var con_test = confirm("선택한 시험정보를 삭제할까요?");
		if (con_test == true) {
			location.assign("sublist_delete.jsp?sid=" + value + "&subid="
					+ subid + "&scount=" + scount);
		}
	}

	
</script>

</head>
<body>
	<div class="container">
		<h2>
			시험<small>v1.0</small>
		</h2>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">쌍용교육센터</a>
				</div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">과목 관리</a></li>
					<li><a href="#">문제 관리</a></li>
					<li><a href="#">체점 관리</a></li>
					<li><a href="#">[관리자/admin]로그아웃</a></li>
				</ul>
			</div>
		</nav>
		<div class="panel panel-default">
			<div class="panel-heading">Subject Upload</div>
			<div class="panel-body">
				<%=message%>
				<%=message2%>
				<form action="sublist_insert.jsp" method="post" id="form_">
					<div class="form-group">
						<label for="subjectid_">과목 번호:</label> <input type="text"
							class="form-control" name="subjectid_" id="subjectid_">
					</div>
					<div class="form-group">
						<label for="sname_">과목명:</label> <input type="text"
							class="form-control" name="sname_" id="sname_">
					</div>
					<div class="form-group">
						<label for="sdate_">과목기간:</label> <input type="text"
							class="form-control" name="sdate_" id="sdate_">
					</div>
					<div class="form-group">
						<label for="scount_">차수:</label> <select class="form-control"
							name="scount_" id="scount_">
							<option value="1">1차</option>
							<option value="2">2차</option>
							<option value="3">3차</option>
						</select>
					</div>
					<button type="submit" id="btnSublist" class="btn btn-default">등록</button>
				</form>

			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">Subject List</div>
			<div class="panel-body">
				<%=message1%>
				<table class="table">
					<thead>
						<tr>
							<th>과목 번호</th>
							<th>과목명</th>
							<th>기간</th>
							<th>차수</th>
							<th>시험문제개수</th>
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
</body>
</html>