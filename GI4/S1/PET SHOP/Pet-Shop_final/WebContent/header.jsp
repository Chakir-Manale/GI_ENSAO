<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	

<c:if test="${!empty param.theLocale }">
<c:set var="theLocale" 
value="${param.theLocale}" scope="session" />
</c:if>

<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="petshop.i18n.translate.mylabels" />



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="petshop.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pet Shop</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>

<div id="wrap">
   <div class="header">
       		<div class="logo"><a href="index.jsp"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>            
        <div id="menu">
            <ul>                                                                       
            <li><a href="index.jsp?theLocale=<%=session.getAttribute("theLocale")%>"><fmt:message key="header.home"/></a></li>
            <li><a href="about.jsp?theLocale=<%=session.getAttribute("theLocale")%>"><fmt:message key="header.aboutus"/></a></li>
            <li><a href="category.jsp?theLocale=<%=session.getAttribute("theLocale")%>"><fmt:message key="header.pets"/></a></li>
            <li><a href="specials.jsp?theLocale=<%=session.getAttribute("theLocale")%>"><fmt:message key="header.specialspets"/></a></li>
            <li><a href="myaccount.jsp?theLocale=<%=session.getAttribute("theLocale")%>"><fmt:message key="header.myaccout"/></a></li>
            <li><a href="register.jsp?theLocale=<%=session.getAttribute("theLocale")%>"><fmt:message key="header.register"/></a></li>
            <li><a href="details.jsp?theLocale=<%=session.getAttribute("theLocale")%>"><fmt:message key="header.prices"/></a></li>
            <li><a href="contact.jsp?theLocale=<%=session.getAttribute("theLocale")%>"><fmt:message key="header.contact"/></a></li>
         	<li class="menu__userInfo">
	            	<a href="myaccount.jsp">
		 		       
		 		        	<img src="images/Login.jpg" alt="login-in" />	       	            		
		            		<strong>
	            			 	<%
		            		 		if("".equals(session.getAttribute("utilisateur"))){
		            		 			out.print("<em>login</em>");
		            		 		}else if(session.getAttribute("utilisateur") != null) {
				            			//out.print(((Utilisateur) session.getAttribute("utilisateur")).getUsername());
				            			out.print(session.getAttribute("utilisateur"));
				            		}
	            			 	
				            	%>
			            	</strong>
			            
		        	</a>
	            </li>  
	            <li>
	            	<a href="logout.jsp">
            			<%
            				if(session.getAttribute("utiisateur") != null 
            					&& !("".equals(session.getAttribute("utilisateur")))){
            					
            					out.print("<em>logout</em>");
            				}
            			%>
   	            	</a>
	            </li>  
	        
            </ul>
        </div>     
            
            
       </div> 
       </div>
<!--  -->