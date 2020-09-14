<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<title>Bankonet</title>
</head>
<body>
<header>
<center><h1><em><b>Identification</b></em></h1></center>
</header>
<div class="container">
<form method="post" action="login" class="form-group">
<div>
<span style="color:red">${ error ? "Le client est inconnue." : ""}</span>
<br>
<label for="nom">Login : </label>
<input name="login" type="text" class="form-control" placeholder="Login"><br>
<label for="prenom" >Password : </label>
<input type="password" name="password" class="form-control" placeholder="Password"><br>
</div><br>
<input class="btn btn-primary" type="submit" value="Valider">
<input type="reset" class="btn btn-primary" value="Tout effacer">

</form>
</div>
</body>
</html>