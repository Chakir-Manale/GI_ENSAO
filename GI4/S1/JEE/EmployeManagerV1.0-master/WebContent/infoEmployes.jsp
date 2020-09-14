<%@page import="ma.jberrich.model.Employe"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employe Manager</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<jsp:useBean id="liste" class="ma.jberrich.model.ListeEmployes" scope="request"/>
</head>
<body style="margin: 10px;">
	<legend> Liste Employes :</legend>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>Nom</th>
				<th>Age</th>
				<th>Fonction</th>
				<th>Salaire</th>
				<th>Service</th>
			</tr>
		</thead>
		<tbody>
			<% for(Employe employe:liste.getEmployes()) {
			%>
				<tr>
					<th scope=row><%= employe.getId() %></th>
					<td><%= employe.getNom() %></td>	
					<td><%= employe.getAge() %></td>	
					<td><%= employe.getFonction() %></td>	
					<td><%= employe.getSalaire() %></td>	
					<td><%= employe.getService().getNom() %></td>	
				</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<legend> </legend>
	<a class="btn btn-primary btn-success" href="home.jsp" role="button">Retour</a>
</body>
</html>