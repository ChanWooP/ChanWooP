<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*, com.connection.*"%>
<%
	request.setCharacterEncoding("utf-8");
	String mid_ = request.getParameter("mid_");
	String name_ = request.getParameter("name_");
	String phone = request.getParameter("phone");
	String email = request.getParameter("email");
	
	StringBuilder sb = new StringBuilder();
	Connection conn = null;
	PreparedStatement stmt = null;
	
	try {
		conn = MySQLConnection80.connect();
		String sql = "INSERT INTO Members (mid_, name_, phone, email)\r\n" + 
				"VALUES(?, ?, ?, ?)";
		
		stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, mid_);
		stmt.setString(2, name_);
		stmt.setString(3, phone);
		stmt.setString(4, email);
		
		int count = stmt.executeUpdate();
		
		sb.append("입력성공!");
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
		<%= sb.toString() %>
	</div>
</body>
</html>