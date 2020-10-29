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
	<div class="container">
		<h2>Welcome, ${user.name}</h2>
		<form method="post" action="users">
			<input type="hidden" name="_method" value="put" />
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
						<input type="text" value="${ user.password }" class="form-control" id="password" name="password" />
					</td>
				</tr>
			</table>
			<input type="submit" class="btn btn-primary" id="updateProfileBtn" value="Update Profile" />
		</form>
		<form method="get" action="signout">
			<input type="submit" value="Sign Out" class="btn btn-secondary" />
		</form>
	</div>
</body>
</html>