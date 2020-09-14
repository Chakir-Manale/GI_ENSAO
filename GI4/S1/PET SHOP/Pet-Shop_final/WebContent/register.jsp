<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	

<c:if test="${!empty param.theLocale }">
<c:set var="theLocale" 
value="param.theLocale" scope="session" />
</c:if>

<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="petshop.i18n.translate.mylabels" />

     
       <%@ include  file="header.jsp" %>
   
       
       
       <div class="center_content">
       	<div class="left_content">
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span><fmt:message key="register.register"/></div>
        
        	<div class="feat_prod_box_details">
            <p class="details">
             Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.
            </p>
            
              	<div class="contact_form">
                <div class="form_subtitle"><fmt:message key="register.createnewaccount"/></div>
                
                 <form name="register" action="Controller"> 
		                 <div class="form_row" id="error" style="dispay:none">
		                     <font color=red> <fmt:message key="register.Pleasefillallthefields"/> </font>         
		                  </div>  
		                 
                   <div class="form_row">
                    <label class="contact"><strong><fmt:message key="register.username"/>:</strong></label>
                    <input type="text" class="contact_input" name="Username"/>
                    </div>  


                    <div class="form_row">
                    <label class="contact"><strong><fmt:message key="register.password"/>:</strong></label>
                    <input type="password" class="contact_input" name="Password"/>
                    </div> 

                    <div class="form_row">
                    <label class="contact"><strong><fmt:message key="register.email"/>:</strong></label>
                    <input type="email" class="contact_input" name="Email"/>
                    </div>


                    <div class="form_row">
                    <label class="contact"><strong><fmt:message key="register.phone"/>:</strong></label>
                    <input type="text" class="contact_input" name="Phone" />
                    </div>
                    
                    <div class="form_row">
                    <label class="contact"><strong><fmt:message key="register.company"/>:</strong></label>
                    <input type="text" class="contact_input" name="Company"/>
                    </div>
                    
                    <div class="form_row">
                    <label class="contact"><strong><fmt:message key="register.adresse"/>:</strong></label>
                    <input type="text" class="contact_input" name="Adresse"/>
                    </div>                    

                    <div class="form_row">
                        <div class="terms">
                        <input type="checkbox" name="terms" />
                        <fmt:message key="register.Iagreetothe"/> <a href="#">terms &amp; <fmt:message key="register.conditions"/></a>                        </div>
                    </div> 

                    
                    <div class="form_row">
                    <input type="submit" class="register"  name="Requete" value="<fmt:message key="register.register"/>" />
                    </div>   
                  </form>     
                </div>  
            
            <% 
            if(""!=session.getAttribute("error-emptyFields")){
            	
             out.println("<style >#error{display=block;} </style>");
			 session.removeAttribute("error");
            }
            %>
            
          </div>	
            
              

            

            
         <div class="clear"></div>
        </div>
        <!--end of left content-->
        
        <!--end of right content-->
               <%@ include  file="right.jsp" %>
        
        
        
       
       
       <div class="clear"></div>
       </div>
       
       
        <!-- footer content-->
       
              
        <%@ include  file="footer.jsp" %>
    
