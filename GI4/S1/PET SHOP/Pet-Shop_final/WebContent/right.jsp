<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	

<c:if test="${!empty param.theLocale }">
<c:set var="theLocale" 
value="${param.theLocale}" scope="session" />
</c:if>

<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="petshop.i18n.translate.mylabels" />


<div class="right_content">
        	<div class="languages_box">
            <span class="red"><fmt:message key="right.languages"/>:</span>
            <% String current=request.getRequestURI();%>
            <a href="<%=current%>?theLocale=en_US" class="<fmt:message key="right.selected_en"/>"><img src="images/gb.gif" alt="" title="" border="0" /></a>
            <a href="<%=current%>?theLocale=fr_FR" class="<fmt:message key="right.selected_es"/>"><img src="images/fr.gif" alt="" title="" border="0" /></a>
            <a href="<%=current%>?theLocale=de_DE" class="<fmt:message key="right.selected_de"/>"><img src="images/de.gif" alt="" title="" border="0" /></a>
            </div>
                <div class="currency">
                <span class="red"><fmt:message key="right.currency"/>: </span>
                <a href="#" class="<fmt:message key="right.selected_de"/>">GBP</a>
                <a href="#" class="<fmt:message key="right.selected_es"/>">EUR</a>
                <a href="#" class="<fmt:message key="right.selected_en"/>">USD</a>
                </div>
                
                
              <div class="cart">
                  <div class="title"><span class="title_icon"><img src="images/cart.gif" alt="" title="" /></span><fmt:message key="right.mycart"/></div>
                  <div class="home_cart_content">
                  3 x <fmt:message key="right.items"/> | <span class="red"><fmt:message key="right.total"/></span>
                  </div>
                  <a href="cart.jsp" class="view_cart"><fmt:message key="right.viewcart"/></a>
              
              </div>
                       
            	
        
        
             <div class="title"><span class="title_icon"><img src="images/bullet3.gif" alt="" title="" /></span><fmt:message key="right.aboutourshop"/></div> 
             <div class="about">
             <p>
             <img src="images/about.gif" alt="" title="" class="right" />
             Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.
             </p>
             
             </div>
             
             <div class="right_box">
             
             	<div class="title"><span class="title_icon"><img src="images/bullet4.gif" alt="" title="" /></span><fmt:message key="right.promotions"/></div> 
                    <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="right.productname"/></a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                        <a href="details.jsp"><img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>
                    
                    <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="right.productname"/></a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                        <a href="details.jsp"><img src="images/thumb2.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>                    
                    
                    <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="right.productname"/></a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                        <a href="details.jsp"><img src="images/thumb3.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>              
             
             </div>
             
             <div class="right_box">
             
             	<div class="title"><span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span><fmt:message key="right.categories"/></div> 
                
                <ul class="list">
                <li><a href="#"><fmt:message key="right.accesoires"/></a></li>
                <li><a href="#"><fmt:message key="right.petsgifts"/></a></li>
                <li><a href="#"><fmt:message key="right.specials"/></a></li>
                <li><a href="#"><fmt:message key="right.hollidaysgifts"/></a></li>
                <li><a href="#"><fmt:message key="right.accesoires"/></a></li>
                <li><a href="#"><fmt:message key="right.petsgifts"/></a></li>
                <li><a href="#"><fmt:message key="right.specials"/></a></li>
                <li><a href="#"><fmt:message key="right.hollidaysgifts"/></a></li>
                <li><a href="#"><fmt:message key="right.accesoires"/></a></li>
                <li><a href="#"><fmt:message key="right.petsgifts"/></a></li>
                <li><a href="#"><fmt:message key="right.specials"/></a></li>                                              
                </ul>
                
             	<div class="title"><span class="title_icon"><img src="images/bullet6.gif" alt="" title="" /></span><fmt:message key="right.partners"/></div> 
                
                <ul class="list">
                <li><a href="#"><fmt:message key="right.accesoires"/></a></li>
                <li><a href="#"><fmt:message key="right.petsgifts"/></a></li>
                <li><a href="#"><fmt:message key="right.specials"/></a></li>
                <li><a href="#"><fmt:message key="right.hollidaysgifts"/></a></li>
                <li><a href="#"><fmt:message key="right.accesoires"/></a></li>
                <li><a href="#"><fmt:message key="right.petsgifts"/></a></li>
                <li><a href="#"><fmt:message key="right.specials"/></a></li>
                <li><a href="#"><fmt:message key="right.hollidaysgifts"/></a></li>
                <li><a href="#"><fmt:message key="right.accesoires"/></a></li>                              
                </ul>      
             
             </div>         
             
</div>
