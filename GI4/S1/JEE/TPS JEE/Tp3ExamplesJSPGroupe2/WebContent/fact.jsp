<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TP 3 : JSP</title>
</head>
<body>
<h1>Table des factorielles</h1>
<%
	int i=1,fact=1;
	for(i=1,fact=1 ; i<10;i++,fact*=i){
		out.print(i+" ! = " +fact+ "<br>");
	}
	
%>
</body>
</html>