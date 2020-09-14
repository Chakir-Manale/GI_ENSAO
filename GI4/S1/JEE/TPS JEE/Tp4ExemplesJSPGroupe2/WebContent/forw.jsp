<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TP 4 : JSP</title>
</head>
<body>
<h2>JSP déléguée</h2>
<hr>
<h2> Hello, <%= request.getParameter("name") %></h2>

</body>
</html>