<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%!
String user;

%>
<%
user= request.getParameter("user");
session.setAttribute("user",user);

%>

<%@include file="header.jsp"%>

<%@include file="body.jsp"%>
<jsp:include page="footer.jsp" >
    <jsp:param name="protocol" value="<%= request.getProtocol()%>"/>
    <jsp:param name="scheme" value="<%= request.getScheme() %>"/>
    <jsp:param name="servername" value="<%= request.getServerName() %>"/>
    <jsp:param name="serverport" value="<%= request.getServerPort() %>"/>
    <jsp:param name="remoteaddr" value="<%= request.getRemoteAddr() %>"/>
    <jsp:param name="remotehost" value="<%= request.getRemoteHost() %>"/>
    <jsp:param name="method" value="<%= request.getMethod() %>"/>
              
</jsp:include>