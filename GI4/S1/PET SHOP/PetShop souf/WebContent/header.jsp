<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="ma.petshop.User"  %>

<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="com.example.i18n.resources.mylabels"/>
       <div class="header"> 
       		<div class="logo"><a href="index.jsp"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>            
        <div id = "menu">
            <ul>                                                                       
	            <li class="selected"><a href="index.jsp"><fmt:message key="label.home"/></a></li>
	            <li><a href="about.jsp"><fmt:message key="label.aboutUs"/></a></li>
	            <li><a href="category.jsp"><fmt:message key="label.pets"/></a></li>
	            <li><a href="specials.jsp"><fmt:message key="label.specialPets"/></a></li>
	            <li><a href="myaccount.jsp"><fmt:message key="label.myAccount"/></a></li>
	            <li><a href="registration.jsp"><fmt:message key="label.register"/></a></li>
	            <li><a href="details.jsp"><fmt:message key="label.prices"/></a></li>
	            <li><a href="contact.jsp"><fmt:message key="label.contact"/></a></li>
	            <li class="menu__userInfo">
	            	<a href="myaccount.jsp">
		 		        <div>  
		 		        
		 		        	<img src="images/logedIn.svg" alt="login icon" />	       	            		
		            		<strong>
	            			 	<%
		            		 		if("".equals(request.getParameter("currentSessionUser"))){
		            		 			session.setAttribute("currentSessionUser", "");
		            		 			out.print("<em>login</em>");
		            		 		}else if(session.getAttribute("currentSessionUser") != null && !("error".equals(session.getAttribute("currentSessionUser")))) {
				            			out.print(((User) session.getAttribute("currentSessionUser")).getUsername());
				            			//out.print("<a href='logout.jsp'><img src=\"images/logout.svg\" alt=\"login icon\" /></a>");
				            		} else {
				            			out.print("<em>login</em>");
				            		}
				            	%>
			            	</strong>
			            </div>
		        	</a>
	            </li>  
	            <li>
	            	<a href="logout.jsp">
            			<%
            				if(session.getAttribute("currentSessionUser") != null 
            					&& !("error".equals(session.getAttribute("currentSessionUser")))
            					&& !("".equals(session.getAttribute("currentSessionUser")))){
            					
            					out.print("<em>logout</em>");
            				}
            			%>
   	            	</a>
	            </li>
	            </li>   
	        </ul>
     </div>     
</div> 