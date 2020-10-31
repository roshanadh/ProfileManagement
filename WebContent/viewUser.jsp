<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<title>${ username } | Profile Management System</title>
</head>
<body>
	<div class="jumbotron d-flex align-items-center">
		<div class="container">
			<c:choose>
				<c:when test="${param.searchError == 'unknown-username' }">
					<p class='alert alert-danger'>Username not found!</p>
				</c:when>
				<c:otherwise>
					<h2 class="text-center">Profile of ${ user.username }</h2>
					<table class="table table-striped">
						<tr>
							<th>Name</th>
							<td>
								${ user.name }
							</td>
						</tr>
						<tr>
							<th>Username</th>
							<td>
								${ user.username }
							</td>
						</tr>
						<tr>
							<th>Email</th>
							<td>
								${ user.email }
							</td>
						</tr>
						<tr>
							<th>Address</th>
							<td>
								${ user.address }
							</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
			<form method="get" action="login">
				<input type="submit" value="Go back to your profile" class="btn btn-primary" />
			</form>
		</div>
	</div>
</body>
</html>