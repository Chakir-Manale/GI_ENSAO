<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	

<c:if test="${!empty param.theLocale }">
<c:set var="theLocale" 
value="param.theLocale" scope="session" />
</c:if>

<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="petshop.i18n.translate.mylabels" />

 <%@ page import="petshop.*" %>
  
<jsp:useBean id="utilisateur" class="petshop.Utilisateur"></jsp:useBean>
	        
<div class="left_content">
            	<div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span><fmt:message key="sessionon.welcometoyouraccount"/> !</div>
	                 <div class='form_profile' id='profile' style='display:none'>
					     <p class='details'> <fmt:message key="sessionon.hello"/> <%= request.getParameter("User") %></p>
					  <div > </div>
				</div>
				
				<% utilisateur=Traitement.SearchUser(request.getParameter("User"));%> 
				 	
				
        	<div class="feat_prod_box_details">
           	        
	              <p class="details" id="p-details" style="color:green"><fmt:message key="sessionon.sessionon"/> ! 
	              <fmt:message key="sessionon.sessionon.welcome"/> <%= utilisateur.getUsername()%> </p>
              	<div class="contact_form" id="Connexform">
			                
                	<div class="form_subtitle" ><fmt:message key="sessionon.yourinformations"/> : </div>
                	
                    <form name="register" action="Controller" method ="post" >          
	                       <div class='form_row'>
	                        <label class="contact"><strong><fmt:message key="sessionon.username"/>:</strong></label>
		                   <label class='contact'><strong> <%= utilisateur.getUsername() %></strong></label>
		                   </div>
		                   
		                   
						   <div class='form_row'>
						   <label class="contact"><strong><fmt:message key="sessionon.email"/>:</strong></label>
		                   <label class='contact'><strong> <%=utilisateur.getEmail()%> </strong></label>
		                   </div>
		                   
		                   
						   <div class='form_row'>
						   <label class="contact"><strong><fmt:message key="sessionon.phone"/> :</strong></label>
		                   <label class='contact'><strong> <%=utilisateur.getPhone()%> </strong></label>
		                   
		                   
		                   </div>
						   <div class='form_row'>
						   <label class="contact"><strong><fmt:message key="sessionon.company"/>:</strong></label>
		                   <label class='contact'><strong> <%=utilisateur.getCompany()%> </strong></label>
		                   </div>
		                   
		                   
						   <div class='form_row'>
						   <label class="contact"><strong><fmt:message key="sessionon.adresse"/>:</strong></label>
		                   <label class='contact'><strong> <%=utilisateur.getAdresse()%> </strong></label>
		                   </div>
		                   
		                   <div class="form_row">
		                   <input type="submit"  name="Requete" value="LogOut" /> 
	                       </div> 
		                    
					    
                    
                 	 </form>     
                </div> 
                 
                 <div class="clear"></div>
        </div>
</div>