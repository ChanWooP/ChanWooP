<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*, com.connection.*"%>
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
		<form action="test042_receive.jsp" method="post">
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
</body>
</html>