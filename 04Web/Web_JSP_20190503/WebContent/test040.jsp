<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*, com.connection.*" %>
<%
	//[문제]JSP _ 회원정보 출력 기능 구현
	//회원번호(PK), 이름, 전화번호, 이메일로 구성된 테이블의 자료를 읽어와서 웹페이지에 출력하는 JSP 코드 작성.
	
	StringBuilder sb = new StringBuilder();
	Connection conn = null;
	PreparedStatement stmt = null;
	try {
		conn = MySQLConnection80.connect();
		String sql = "SELECT mid_, name_, phone, email FROM members";
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			sb.append(String.format("<tr>"));
			sb.append(String.format("<td>%s</td>", rs.getString("mid_")));
			sb.append(String.format("<td>%s</td>", rs.getString("name_")));
			sb.append(String.format("<td>%s</td>", rs.getString("phone")));
			sb.append(String.format("<td>%s</td>", rs.getString("email")));
			sb.append(String.format("</tr>"));
		}
		rs.close();
	} catch (Exception e) {
		System.out.println(e);
	} finally {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se) {
		}
		try {
			MySQLConnection80.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
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
		// jQuery methods go here...
	});
	function myFunction() {
	}
</script>
</head>
<body>

	<div class="container">

		<h1>회원관리 v2.0</h1>
		<div>
			<table class="table">
				<thead>
					<tr>
						<th>MID</th>
						<th>NAME</th>
						<th>PHONE</th>
						<th>EMAIL</th>
					</tr>
				</thead>
				<tbody>
					<%=sb.toString()%>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>