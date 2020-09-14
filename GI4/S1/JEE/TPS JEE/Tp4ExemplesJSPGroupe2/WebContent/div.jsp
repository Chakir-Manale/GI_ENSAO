<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="err.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Division</title>
</head>
<body bgcolor="orange" text="yellow">

<h2>Test d'une erreur</h2>
<br>
<% int rand = (int) (Math.random()*2);%>
<h2>Resultat : <%=12/rand %></h2>
</body>
</html>