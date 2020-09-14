<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TP 3 : JSP</title>
</head>
<body>


 <h1>Hello <%= request.getParameter("name") %>,your password is "<%=  request.getParameter("password") %>"</h1> 
</body>
</html>