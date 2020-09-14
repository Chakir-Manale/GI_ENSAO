<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	

<c:if test="${!empty param.theLocale }">
<c:set var="theLocale" 
value="${param.theLocale}" scope="session" />
</c:if>


<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="petshop.i18n.translate.mylabels" />


   <%@ include  file="header.jsp" %>
       
       
       <div class="center_content">
       	<div class="left_content">
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span><fmt:message key="contact.contactus"/></div>
        
        	<div class="feat_prod_box_details">
            <p class="details">
             Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.
            </p>
            
              	<div class="contact_form">
                <div class="form_subtitle"><fmt:message key="contact.allfieldsarerequierd"/></div>          
                    <div class="form_row">
                    <label class="contact"><strong><fmt:message key="contact.name"/>:</strong></label>
                    <input type="text" class="contact_input" />
                    </div>  

                    <div class="form_row">
                    <label class="contact"><strong><fmt:message key="contact.email"/>:</strong></label>
                    <input type="text" class="contact_input" />
                    </div>


                    <div class="form_row">
                    <label class="contact"><strong><fmt:message key="contact.phone"/>:</strong></label>
                    <input type="text" class="contact_input" />
                    </div>
                    
                    <div class="form_row">
                    <label class="contact"><strong><fmt:message key="contact.company"/>:</strong></label>
                    <input type="text" class="contact_input" />
                    </div>


                    <div class="form_row">
                    <label class="contact"><strong><fmt:message key="contact.message"/>:</strong></label>
                    <textarea class="contact_textarea" ></textarea>
                    </div>

                    
                    <div class="form_row">
                    <a href="#" class="contact"><fmt:message key="contact.send"/></a>                    </div>      
                </div>  
            
          </div>	
            
              

            

            
         <div class="clear"></div>
        </div>
        <!--end of left content-->
        
        <!--end of right content-->
        
        
               <%@ include  file="right.jsp" %>
        
       
       
       <div class="clear"></div>
       </div>
       
       
       <!--end of center content-->
       
              

    
        <%@ include  file="footer.jsp" %>