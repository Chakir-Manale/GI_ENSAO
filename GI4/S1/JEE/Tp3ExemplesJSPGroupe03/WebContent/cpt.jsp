<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Compteur</h1>
	<%!
	int cpt = 0;
	int getCpt(){
		return cpt++;
	}
	
	%>
	<h1><%= getCpt() %></h1>
</body>
</html>