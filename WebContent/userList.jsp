<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	
	<title>Profiles</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<h2 class="text-center">Profiles</h2>
		<c:if test="${ !users.isEmpty() }">
			<table class="table table-striped">
				<tr>
					<th>S.N.</th>
					<th>Username</th>
					<th>Name</th>
					<th>Email</th>
					<th>Address</th>
				</tr>
				<% int i = 1; %>
				<c:forEach items="${ users }" var="user">
					<tr>
						<td><% out.print(i++); %></td>
						<td>${ user.username }</td>
						<td>${ user.name }</td>
						<td>${ user.email }</td>
						<td>${ user.address }</td>	
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>