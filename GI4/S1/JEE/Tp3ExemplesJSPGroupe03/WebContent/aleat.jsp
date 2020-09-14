<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	int aleat = (int) (Math.random() * 5);
	
	%>
	<h1> NBr : <% out.print(aleat); %></h1>

</body>
</html>