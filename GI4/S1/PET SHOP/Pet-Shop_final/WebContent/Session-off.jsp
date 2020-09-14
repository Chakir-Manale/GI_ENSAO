<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	




<c:if test="${!empty param.theLocale }">
<c:set var="theLocale" 
value="param.theLocale" scope="session" />
</c:if>
<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="petshop.i18n.translate.mylabels" />



<!--  Cookies , RememberMe  -->
<%
    Cookie[] cookies = request.getCookies();
    String username="";
    String password = "";
    if(cookies!=null)
    {
      for(int i=0;i<cookies.length;i++){
        Cookie cookie = cookies[i];
        if(cookie.getName().equals("username-cookie"))
        {
            username= cookie.getValue();
        }
        else if(cookie.getName().equals("password-cookie"))
        {
            password= cookie.getValue();
        }
      }
    }

   %>

<div class="left_content">
            	<div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span><fmt:message key="sessionoff.myaccount"/></div>
        
        	<div class="feat_prod_box_details">
           	        
	              <p class="details" id="p-details">
            		 Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            	 	 Ut enim ad minim veniam, quis nostrud. Lorem ipsum dolor sit amet, consectetur adipisicing elit,
            	     sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.
           		 </p>
              	<div class="contact_form" id="Connexform">
			                
                	<div class="form_subtitle" ><fmt:message key="sessionoff.loginintoyouraccount"/></div>
                	
                	
                	<!-- div pour afficher un msg d'erreur au cas ou le login/mot de passe est incorrecte!  -->
                	<div    id="error">
                		<em style='color:red' id="ErrorMsg"> </em>
                		<em style='color:red'id="invalid"></em>
                	</div>
                	
                	
                    <form name="register" action="Controller" method ="post" >          
	                    <div class="form_row">
		                    <label class="contact"><strong><fmt:message key="sessionoff.username"/>:</strong></label>
		                    <input type="text" name="Username" class="contact_input" value="<%= username %>"/>
	                    </div>  


	                    <div class="form_row">
		                    <label class="contact"><strong><fmt:message key="sessionoff.password"/>:</strong></label>
		                    <input type="password"  name="Password" class="contact_input" value="<%= password %>"/>
	                    </div>                     

	                    <div class="form_row">
	                        <div class="terms">
	                        <input type="checkbox" name="rememberMe"  />
	                        <fmt:message key="sessionoff.rememberme"/>
	                         </div>
                        </div> 

                    
	                    <div class="form_row">
	                  	  <input type="submit" class="register"  name="Requete" value="login" />
	                    </div>   
                    
                 	 </form> 
	  
        <% 
        String msg1= (String)session.getAttribute("error-EmptyFields");
        String msg2= (String)session.getAttribute("error-InvalidLogin");
                				
        if(""!= msg1){

        	out.print("<script>document.getElementById('ErrorMsg').innerHTML='"+msg1 +"'</script>");
             //out.println("<style >#error{display=block;} </style>");
			 session.removeAttribute("error-EmptyFields");
			
            }
        else if(msg2 !="") {
        	out.print("<script>document.getElementById('invalid').innerHTML='"+msg2 +"'</script>");
        	// out.println("<style >#invalid{display=block;} </style>");
 			// out.println("<script> alert('Please fill all the fields'); </script>");
 			 session.removeAttribute("error-InvalidLogin");
 			 
        }
            %>    
                 </div>  
                 
                 <div class="clear"></div>
        </div>
        </div>
          
      