<%@page import="ma.jberrich.model.Service"%>
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

<jsp:useBean id="liste" class="ma.jberrich.model.ListeServices" scope="request"/>
</head>
<body style="margin: 10px;">

	<form method="post" action="TraitementEmploye" class="form-horizontal">
		<fieldset>

			<!-- Form Name -->
			<legend> Ajouter Employe :</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nom">Nom :</label>
				<div class="col-md-4">
					<input id="nom" name="nom" type="text" placeholder="Jamal BERRICH"
						class="form-control input-md" required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="age">Age :</label>
				<div class="col-md-4">
					<input id="age" name="age" type="text" placeholder=""
						class="form-control input-md" required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="fonction">Fonction
					:</label>
				<div class="col-md-4">
					<input id="fonction" name="fonction" type="text" placeholder=""
						class="form-control input-md" required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="salaire">Salaire
					:</label>
				<div class="col-md-4">
					<input id="salaire" name="salaire" type="text" placeholder=""
						class="form-control input-md" required="">

				</div>
			</div>

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="service">Service
					:</label>
				<div class="col-md-4">
					<select id="service" name="service" class="form-control">
						<% 
							for(Service service:liste.getServices()) {
						%>
								<option value="<%= service.getNom() %>"><%= service.getNom() %></option>
						<%
							}
						%>
					</select>
				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="singlebutton"></label>
				<div class="col-md-4">
					<button id="singlebutton" name="singlebutton"
						class="btn btn-primary">Envoyer</button>
				</div>
			</div>

		</fieldset>
	</form>

</body>
</html>