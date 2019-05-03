<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*, com.connection.*"%>
<%
	StringBuilder sb = new StringBuilder();
	StringBuilder sb1 = new StringBuilder();
	Connection conn = null;
	PreparedStatement stmt = null;
	int count = 0;
	String result = request.getParameter("result");

	if (result == null) {
		result = " ";
	} else if (result.equals("success")) {
		result = "<div class='alert alert-success alert-dismissible alert-dismissible' >"
				+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
				+ "<strong>Success!</strong> 입력성공." + " </div>";
	} else if (result.equals("faile")) {
		result = "<div class='alert alert-danger alert-dismissible alert-dismissible' >"
				+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
				+ "<strong>Danger!</strong> 입력실패." + " </div>";
	}

	try {
		conn = MySQLConnection80.connect();

		String sql2 = "SELECT mid_, name_, phone, email FROM members";
		stmt = conn.prepareStatement(sql2);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			sb.append(String.format("<tr>"));
			sb.append(String.format("<td>%s</td>", rs.getString("mid_")));
			sb.append(String.format("<td>%s</td>", rs.getString("name_")));
			sb.append(String.format("<td>%s</td>", rs.getString("phone")));
			sb.append(String.format("<td>%s</td>", rs.getString("email")));
			sb.append(String.format("</tr>"));
			count++;
		}
		rs.close();
		sb1.append(String.format(
				"<button type='button' class='btn btn-default'>TOTAL<span class='badge'>%d</span></button>",
				count));

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
	<div class="container">
		<h1>회원관리 v2.0 by PCW</h1>
		<div class="panel panel-default">
			<div class="panel-heading">회원입력</div>
			<div class="panel-body">
				<%=result%>
				<form action="test045_insert.jsp" method="post">
					<div class="form-group">
						<label for="mid_">mid_:</label> <input type="text"
							class="form-control" id="mid_" name="mid_">
					</div>
					<div class="form-group">
						<label for="mid_">name_:</label> <input type="text"
							class="form-control" id="name_" name="name_">
					</div>
					<div class="form-group">
						<label for="phone">phone:</label> <input type="tel"
							class="form-control" id="phone" name="phone">
					</div>
					<div class="form-group">
						<label for="email">email:</label> <input type="email"
							class="form-control" id="email" name="email">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">회원목록</div>

			<div class="panel-body">
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
				<%=sb1.toString()%>
			</div>
		</div>
	</div>
</body>
</html>