<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' href='style.css'/>
</head>
<body>
	<%
		String name = request.getParameter("name");
		String pswd = request.getParameter("password");
		
	%>
	<h1>Hello <%= name %> your password is <%= pswd %></h1>

</body>
</html>