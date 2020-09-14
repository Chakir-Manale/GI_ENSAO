<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>
<%!
int globaleCounter=0;
Date startDate;
public void jspInit(){
	startDate=new Date();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TP 4 : JSP</title>
</head>
<body>
<h2>
	<% globaleCounter++; %>
	Cette page a été visité : <%= globaleCounter  %> fois
	depuis le <%= startDate %> 
</h2>
</body>
</html>