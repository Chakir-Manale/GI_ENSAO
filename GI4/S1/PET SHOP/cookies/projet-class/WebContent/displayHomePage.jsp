<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Display Details</title>
</head>
<body>
    <%
    String username=request.getParameter("username");
    String password=request.getParameter("password");
String message="Username is : "+ username + "<br/> Password is :" + password ;
    String rememberMe=  request.getParameter("rememberMe");
    if(rememberMe!=null)
    {
        Cookie usernameCookie = new Cookie("username-cookie", username);
        Cookie passwordCookie = new Cookie("password-cookie", username);
        usernameCookie.setMaxAge(24*60*60);
        passwordCookie.setMaxAge(24*60*60);
        response.addCookie(usernameCookie);
        response.addCookie(passwordCookie);
        }
    %>    
    <strong>
    <%= message %>
    </strong>
</body>
</html>