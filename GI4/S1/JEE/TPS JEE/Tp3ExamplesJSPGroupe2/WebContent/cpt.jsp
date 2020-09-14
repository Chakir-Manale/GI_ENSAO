<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TP 3 : JSP</title>
</head>
<body>
<h1>Compteur</h1>
<%!
int cpt=0;
int getCpt(){
	return cpt++;
}

%>

<h1><%= getCpt() %></h1>
</body>
</html>