<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.*,java.text.*" %>
<%!
	String startDate= new Date().toString();

%>
<h1>Date du premier appel : <%= startDate %><br>
Date de l'appel : <%= new Date() %>

</h1>

</body>
</html>