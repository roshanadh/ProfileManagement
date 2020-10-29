<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	
	<title>Hello World!</title>
</head>
<body>
	<div class="jumbotron text-center">
		<h2>Welcome to Profile Management System!</h2>
		<a href="register.jsp">Register</a>&nbsp;
		<a href="users">Show profiles</a>
	</div>
	<div class="container">
		<h2>Sign In</h2>
		<form class="form sign-in-form" method="post" action="signin">
			<div class="form-group">
				<label for="username">Username</label>
				<input type="text" class="form-control" name="username" id="username" />
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" class="form-control" name="password" id="password" />
			</div>
			<input type="submit" class="btn btn-primary" value="Sign In" />
		</form>
	</div>
</body>
</html>