<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*, com.connection.*"%>
<%
	//데이터 수신
	//주의-최초 실행시 수신할 데이터가 없다
	String pageNum = request.getParameter("pageNum");
	if(pageNum == null){
		pageNum = "2";
	}
	
	System.out.println(pageNum);
%>
<!DOCTYPE html>
<html>
<head>
<title>JSP</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<style></style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<div class="container ">
		<h1 class="hea01">
			<img
				src="https://delivery.burgerking.co.kr/resources/images/main/main_logo.png">부트스트랩
		</h1>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<ul class="nav navbar-nav">
					<li><a href="#">방명록 관리</a></li>
					<li class="active"><a href="#">사진 관리</a></li>
					<li><a href="#">로그아웃</a></li>
				</ul>
			</div>
		</nav>
		<div class="panel panel-default">
			<div class="panel-heading">사진 업로드</div>
			<div class="panel-body">
				<input type="text" class="form-control" id="pic"
					placeholder="사진설명 50자내외"> <input type="file"
					class="form-control" id="usr"> <span class="help-block">(only
					.jpg or .png, 1M btye 이내)</span>
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">사진 목록</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-4">
						<div class="thumbnail">
							<a
								href="https://delivery.burgerking.co.kr/resources/images/main/main_logo.png"
								target="_blank"> <img
								src="https://delivery.burgerking.co.kr/resources/images/main/main_logo.png"
								alt="Lights" style="width: 50px">
								<div class="caption">
									<p>버거킹.</p>
								</div>
							</a>
						</div>
					</div>
					<div class="col-md-4">
						<div class="thumbnail">
							<a
								href="https://delivery.burgerking.co.kr/resources/images/main/main_logo.png"
								target="_blank"> <img
								src="https://delivery.burgerking.co.kr/resources/images/main/main_logo.png"
								alt="Nature" style="width: 50px">
								<div class="caption">
									<p>L버거킹.</p>
								</div>
							</a>
						</div>
					</div>
					<div class="col-md-4">
						<div class="thumbnail">
							<a
								href="https://delivery.burgerking.co.kr/resources/images/main/main_logo.png"
								target="_blank"> <img
								src="https://delivery.burgerking.co.kr/resources/images/main/main_logo.png"
								alt="Fjords" style="width: 50px">
								<div class="caption">
									<p>버거킹.</p>
								</div>
							</a>
						</div>
					</div>
				</div>
				<ul class="pager">
					<li><a href="test043_send.jsp?pageNum=1">Previous</a></li>
					<li><a href="test043_send.jsp?pageNum=3">Next</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>