<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	
	<title>Home | Profile Management System</title>
</head>
<body>
	<div class="container ">
	
		<nav class="navbar navbar-light bg-light justify-content-between mt-sm-2">
			<span class="navbar-brand">Welcome, ${ user.name }!</span>
			<form method="get" action="logout">
				<input type="submit" value="Logout" class="btn btn-danger" />
			</form>
			<form class="form-inline" method="get" action="search">
				<input class="form-control mr-sm-2" type="search" placeholder="Search" name="username" />
				<input type="submit" value="Search" class="btn btn-primary" />
			</form>
		</nav>
		
		<form method="post" action="users">
			<%
				String updateError = request.getParameter("updateError");
	
			if (updateError != null) {
				if (updateError.equals("username-exists")) {
					out.print("<p class='alert alert-danger'>Username already exists!</p>");
				} else if (updateError.equals("email-exists")) {
					out.print("<p class='alert alert-danger'>Email already exists!</p>");
				} else if (updateError.equals("constraint-violation")) {
					out.print("<p class='alert alert-danger'>Some error occurred!</p>");
				} else if (updateError.equals("name-cannot-be-null")) {
					out.print("<p class='alert alert-danger'>Name cannot be empty!</p>");
				} else if (updateError.equals("username-cannot-be-null")) {
					out.print("<p class='alert alert-danger'>Username cannot be empty!</p>");
				} else if (updateError.equals("email-cannot-be-null")) {
					out.print("<p class='alert alert-danger'>Email cannot be empty!</p>");
				} else if (updateError.equals("address-cannot-be-null")) {
					out.print("<p class='alert alert-danger'>Address cannot be empty!</p>");
				} else if (updateError.equals("password-cannot-be-null")) {
					out.print("<p class='alert alert-danger'>Password cannot be empty!</p>");
				} else if (updateError.equals("password-too-short")) {
					out.print("<p class='alert alert-danger'>Password must be 8 characters or more!</p>");
				} else {
					out.print("<p class='alert alert-danger'>Some error occurred!</p>");
				}
			}				
			%>
			<!-- Include request method in the form as a hidden input field -->
			<input type="hidden" name="_method" value="put" />
			<!-- Include user's ID in the form as a hidden input field -->
			<input type="hidden" name="id" value="${ user.id }" />
			<table class="table table-striped">
				<tr>
					<th>Name</th>
					<td>
						<input type="text" value="${ user.name }" class="form-control" id="name" name="name" />
					</td>
				</tr>
				<tr>
					<th>Username</th>
					<td>
						<input type="text" value="${ user.username }" class="form-control" id="username" name="username" />
					</td>
				</tr>
				<tr>
					<th>Email</th>
					<td>
						<input type="text" value="${ user.email }" class="form-control" id="email" name="email" />
					</td>
				</tr>
				<tr>
					<th>Address</th>
					<td>
						<input type="text" value="${ user.address }" class="form-control" id="address" name="address" />
					</td>
				</tr>
				<tr>
					<th>Password</th>
					<td>
						<input type="password" value="${ user.password }" class="form-control" id="password" name="password" />
					</td>
				</tr>
			</table>
			<input type="submit" class="btn btn-primary" id="updateProfileBtn" value="Update Profile" />
		</form>
	</div>
</body>
</html>