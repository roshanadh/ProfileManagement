<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	
	<title>Hello World!</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<h2 class="text-center">Sign In</h2>
		<form class="form sign-in-form" method="post" action="login">
			<%
				String loginError = request.getParameter("loginError");
	
				if (loginError != null) {
					if (loginError.equals("unknown-username")) {
						out.print("<p class='alert alert-danger'>Username does not exist!</p>");
					} else if (loginError.equals("bad-credentials")) {
						out.print("<p class='alert alert-danger'>The credentials do not match!</p>");
					} else {
						out.print("<p class='alert alert-danger'>Some error occurred!</p>");
					}
				}
			%>
			<div class="form-group">
				<label for="username">Username</label>
				<input type="text" class="form-control" name="username" id="username" />
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" class="form-control" name="password" id="password" />
			</div>
			<input type="submit" class="btn btn-primary" value="Login" />
		</form>
	</div>
</body>
</html>