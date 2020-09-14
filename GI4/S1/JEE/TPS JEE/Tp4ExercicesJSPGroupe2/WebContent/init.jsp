<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%!
int nombreVisiteurs=0;
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Init</title>
</head>
<body>
<jsp:forward page="identification.jsp">
	<jsp:param name="counter" value="<%= ++nombreVisiteurs %>" />
</jsp:forward>
</body>
</html>