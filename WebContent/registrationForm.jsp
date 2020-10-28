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
	<form class="container" method="post" action="users">
		<h2>Register</h2>
		<div class="form-group">
			<label for="name">Name</label>
			<input type="text" class="form-control" id="name" name="name" placeholder="Your name">
		</div>
		<div class="form-group">
			<label for="address">Address</label>
			<input type="text" class="form-control" id="address" name="address" placeholder="Your address">
		</div>
		<div class="form-group">
			<label for="email">Email</label>
			<input type="email" class="form-control" id="email" name="email" placeholder="hello@contact.me">
		</div>
		<div class="form-group">
			<label for="username">Username</label>
			<input type="text" class="form-control" id="username" name="username" placeholder="Your username">
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" id="password" name="password" placeholder="Your password">
		</div>
		<div>
			<label for="confirmPassword">Confirm Password</label>
			<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm password"> 
		</div>
			
		<input type="submit" class="btn btn-primary" value="Register"/>
	</form>
</body>
</html>