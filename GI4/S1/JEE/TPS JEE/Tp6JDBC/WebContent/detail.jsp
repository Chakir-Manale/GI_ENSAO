<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bankonet</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>

<body>
<h3>Detail du compte </h3><br>
<table class="table table-striped" style="width:40%;" >
<tr>
	<th>Identifiant</th>
	<td>${sessionScope.id}</td>
	
</tr>

<tr>
	<th>Libellé</th>
	<td>${sessionScope.lib}</td>
	
</tr>
<tr>
	<th>Solde</th>
	<td>${sessionScope.solde}</td>
	
</tr>
</table>

</body>
</html>