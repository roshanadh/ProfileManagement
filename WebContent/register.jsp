<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

<title>Register</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h2 class="text-center">Register</h2>
	<form class="container" method="post" action="users">
		<%
			String registerError = request.getParameter("registerError");
	
			if (registerError != null) {
				if (registerError.equals("username-exists")) {
					out.print("<p class='alert alert-danger'>Username already exists!</p>");
				} else if (registerError.equals("email-exists")) {
					out.print("<p class='alert alert-danger'>Email already exists!</p>");
				} else if (registerError.equals("constraint-violation")) {
					out.print("<p class='alert alert-danger'>Some error occurred!</p>");
				} else if (registerError.equals("name-cannot-be-null")) {
					out.print("<p class='alert alert-danger'>Name cannot be empty!</p>");
				} else if (registerError.equals("username-cannot-be-null")) {
					out.print("<p class='alert alert-danger'>Username cannot be empty!</p>");
				} else if (registerError.equals("email-cannot-be-null")) {
					out.print("<p class='alert alert-danger'>Email cannot be empty!</p>");
				} else if (registerError.equals("address-cannot-be-null")) {
					out.print("<p class='alert alert-danger'>Address cannot be empty!</p>");
				} else if (registerError.equals("password-cannot-be-null")) {
					out.print("<p class='alert alert-danger'>Password cannot be empty!</p>");
				} else if (registerError.equals("passwords-do-not-match")) {
					out.print("<p class='alert alert-danger'>Passwords do not match!</p>");
				} else if (registerError.equals("password-too-short")) {
					out.print("<p class='alert alert-danger'>Password must be 8 characters or more!</p>");
				} else {
					out.print("<p class='alert alert-danger'>Some error occurred!</p>");
				}
			}
		%>
		<input type="hidden" name="_method" value="post" />
		<div class="form-group">
			<label for="name">Name</label>
			<input type="text" class="form-control" id="name" name="name" placeholder="Your name" value="${ name }">
		</div>
		<div class="form-group">
			<label for="username">Username</label>
			<input type="text" class="form-control" id="username" name="username" placeholder="Your username" value="${ username }">
		</div>
		<div class="form-group">
			<label for="email">Email</label>
			<input type="email" class="form-control" id="email" name="email" placeholder="hello@contact.me" value="${ email }">
		</div>
		<div class="form-group">
			<label for="address">Address</label>
			<input type="text" class="form-control" id="address" name="address" placeholder="Your address" value="${ address }">
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" id="password" name="password" placeholder="Your password">
		</div>
		<div class="form-group">
			<label for="confirmPassword">Confirm Password</label>
			<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm password"> 
		</div>
		<input type="submit" class="btn btn-primary" value="Register"/>
	</form>
</body>
</html>