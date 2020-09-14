<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%!
String counter;
%>
<html lang="en">
<head>
  <title>Identification</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <style type="text/css">
  .form-control{
  	width:30%;

  	}
  	  	.container{
  	padding:40px;
  }
 
  </style>
</head>
<title>TP 4 : JSP</title>
</head>
<body>
<header>
<center><h1><em><b>Identification</b></em></h1></center>
</header>
<div class="container">
<form method="post" action="login.jsp" class="form-group">
<div>
<%
session.setAttribute("nbrVisiteurs",request.getParameter("counter"));
%>
<h4>Le nombre des visiteurs est : ${param.counter }</h4>
<h4>Veuillez entrer votre nom d'utilisateur et votre mot de passe</h4>
<br>
<label for="nom">Utilisateur : </label>
<input name="user" type="text" class="form-control" placeholder="Votre nom d'utilisateur"><br>
<label for="prenom" >Mot de passe : </label>
<input type="password" name="password" class="form-control" placeholder="Votre mot de passe"><br>
</div><br>
<input class="btn btn-primary" type="submit" value="Enregistrer">
<input type="reset" class="btn btn-primary" value="Tout effacer">

</form>
</div>
</body>
</html>