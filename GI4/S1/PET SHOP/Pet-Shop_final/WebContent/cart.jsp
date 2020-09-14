<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	

<c:if test="${!empty param.theLocale }">
<c:set var="theLocale" 
value="${param.theLocale}" scope="session" />
</c:if>


<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="petshop.i18n.translate.mylabels" />
<html>
<head>
<meta charset="UTF_8"> 
</head>
</html>
       
	   <jsp:include page="header.jsp"></jsp:include> 
      
       
       <div class="center_content">
       	<div class="left_content">
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span><fmt:message key="right.mycart"/></div>
        
        	<div class="feat_prod_box_details">
            
            <table class="cart_table">
            	<tr class="cart_title">
                	<td><fmt:message key="cart.itempic"/></td>
                	<td><fmt:message key="cart.bookname"/></td>
                    <td><fmt:message key="cart.unitprice"/></td>
                    <td><fmt:message key="cart.qty"/></td>
                    <td><fmt:message key="cart.total"/></td>               
                </tr>
                
            	<tr>
                	<td><a href="details.jsp"><img src="images/cart_thumb.gif" alt="" title="" border="0" class="cart_thumb" /></a></td>
                	<td><fmt:message key="cart.books"/></td>
                    <td><fmt:message key="cart.100$"/></td>
                    <td>1</td>
                    <td><fmt:message key="cart.100$"/></td>               
                </tr>          
            	<tr>
                	<td><a href="details.jsp"><img src="images/cart_thumb.gif" alt="" title="" border="0" class="cart_thumb" /></a></td>
                	<td><fmt:message key="cart.books"/></td>
                    <td><fmt:message key="cart.100$"/></td>
                    <td>1</td>
                    <td><fmt:message key="cart.100$"/></td>               
                </tr>                  
            	<tr>
                	<td><a href="details.jsp"><img src="images/cart_thumb.gif" alt="" title="" border="0" class="cart_thumb" /></a></td>
                	<td><fmt:message key="cart.books"/></td>
                    <td><fmt:message key="cart.100$"/></td>
                    <td>1</td>
                    <td><fmt:message key="cart.100$"/></td>                
                </tr>

                <tr>
                <td colspan="4" class="cart_total"><span class="red">TOTAL SHIPPING:</span></td>
                <td> <fmt:message key="cart.250$"/></td>                
                </tr>  
                
                <tr>
                <td colspan="4" class="cart_total"><span class="red">TOTAL:</span></td>
                <td> <fmt:message key="cart.350$"/></td>                
                </tr>                  
            
            </table>
            <a href="#" class="continue">&lt; <fmt:message key="cart.continue"/></a>
            <a href="#" class="checkout"><fmt:message key="cart.checkout"/> &gt;</a>
            
            
            </div>	
            
        
        <div class="clear"></div>
        </div><!--end of left content-->
       <!--end of right content-->
        
               <%@ include  file="right.jsp" %>
    
       
       <div class="clear"></div>
   </div>
       <!--end of center content-->
   
        <%@ include  file="footer.jsp" %>
