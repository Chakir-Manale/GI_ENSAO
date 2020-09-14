<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Date de premier appel : <%! out.println(Calendar.getInstance().getTime()) ; %> </h1>
	<h2>Date d appel : <% out.println(Calendar.getInstance().getTime()) ; %> </h2>
	<h3>Date de premier appel : <% Date = new Date() ;%> </h3>


</body>
</html>