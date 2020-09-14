<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	

<c:if test="${!empty param.theLocale }">
<c:set var="theLocale" 
value="param.theLocale" scope="session" />
</c:if>

<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="petshop.i18n.translate.mylabels" />



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pet Shop</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div id="wrap">

     
       <%@ include  file="header.jsp" %>




       
       <div class="center_content">
       	<div class="left_content">
        	
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span><fmt:message key="specials.specialgifts"/></div>
        
        	<div class="feat_prod_box">
            
            	<div class="prod_img"><a href="details.jsp"><img src="images/prod1.gif" alt="" title="" border="0" /></a></div>
                
                <div class="prod_det_box">
                	<span class="special_icon"><img src="images/special_icon.gif" alt="" title="" /></span>
                	<div class="box_top"></div>
                    <div class="box_center">
                    <div class="prod_title"><fmt:message key="specials.productname"/></div>
                    <p class="details">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation.</p>
                    <a href="details.jsp" class="more">- <fmt:message key="specials.moredetails"/> -</a>
                    <div class="clear"></div>
                    </div>
                    
                    <div class="box_bottom"></div>
                </div>    
            <div class="clear"></div>
            </div>	
            
            
        	<div class="feat_prod_box">
            
            	<div class="prod_img"><a href="details.jsp"><img src="images/prod2.gif" alt="" title="" border="0" /></a></div>
                
                <div class="prod_det_box">
                <span class="special_icon"><img src="images/special_icon.gif" alt="" title="" /></span>
                	<div class="box_top"></div>
                    <div class="box_center">
                    <div class="prod_title"><fmt:message key="specials.productname"/></div>
                    <p class="details">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation.</p>
                    <a href="details.jsp" class="more">- <fmt:message key="specials.moredetails"/> -</a>
                    <div class="clear"></div>
                    </div>
                    
                    <div class="box_bottom"></div>
                </div>    
            <div class="clear"></div>
            </div>
            
            
            
        	<div class="feat_prod_box">
            
            	<div class="prod_img"><a href="details.jsp"><img src="images/prod1.gif" alt="" title="" border="0" /></a></div>
                
                <div class="prod_det_box">
                	<span class="special_icon"><img src="images/special_icon.gif" alt="" title="" /></span>
                	<div class="box_top"></div>
                    <div class="box_center">
                    <div class="prod_title"><fmt:message key="specials.productname"/></div>
                    <p class="details">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation.</p>
                    <a href="details.jsp" class="more">- <fmt:message key="specials.moredetails"/> -</a>
                    <div class="clear"></div>
                    </div>
                    
                    <div class="box_bottom"></div>
                </div>    
            <div class="clear"></div>
            </div>	
            
            
        	<div class="feat_prod_box">
            
            	<div class="prod_img"><a href="details.jsp"><img src="images/prod2.gif" alt="" title="" border="0" /></a></div>
                
                <div class="prod_det_box">
                <span class="special_icon"><img src="images/special_icon.gif" alt="" title="" /></span>
                	<div class="box_top"></div>
                    <div class="box_center">
                    <div class="prod_title"><fmt:message key="specials.productname"/></div>
                    <p class="details">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation.</p>
                    <a href="details.jsp" class="more">- <fmt:message key="specials.moredetails"/> -</a>
                    <div class="clear"></div>
                    </div>
                    
                    <div class="box_bottom"></div>
                </div>    
            <div class="clear"></div>
            </div>            
            
                      <div class="pagination">
            <span class="disabled"></span><span class="current">1</span><a href="#?page=2">2</a><a href="#?page=3">3</a><a href="#?page=199">10</a><a href="#?page=200">11</a><a href="#?page=2">>></a>
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
</div>

</body>
</html>