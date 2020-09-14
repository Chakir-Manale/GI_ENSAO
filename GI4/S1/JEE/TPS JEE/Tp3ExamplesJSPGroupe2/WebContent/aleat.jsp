<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%!
int aleat=((int) (Math.random()* 5));


%>
<h1>Nombre aléatoire : <%= aleat++ %></h1>
</body>
</html>