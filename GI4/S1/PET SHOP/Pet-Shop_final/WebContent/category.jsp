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
        	<div class="crumb_nav">
            <a href="index.jsp"><fmt:message key="category.home"/></a> &gt;&gt; <fmt:message key="category.categoryname"/>
            </div>
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span><fmt:message key="category.categorybooks"/></div>
           
           <div class="new_products">
           
                    <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="category.productname"/></a>
                        <div class="new_prod_bg">
                        <a href="details.jsp"><img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>
                    
                    <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="category.productname"/></a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="images/new_icon.gif" alt="" title="" /></span>
                        <a href="details.jsp"><img src="images/thumb2.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>                    
                    
                    <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="category.productname"/></a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                        <a href="details.jsp"><img src="images/thumb3.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>          


                    <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="category.productname"/></a>
                        <div class="new_prod_bg">
                        <a href="details.jsp"><img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>
                    
                    <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="category.productname"/></a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                        <a href="details.jsp"><img src="images/thumb2.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>                    
                    
                    <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="category.productname"/></a>
                        <div class="new_prod_bg">
                        
                        <a href="details.jsp"><img src="images/thumb3.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div> 
       
                    <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="category.productname"/></a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="images/new_icon.gif" alt="" title="" /></span>
                        <a href="details.jsp"><img src="images/thumb2.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div> 

                    <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="category.productname"/></a>
                        <div class="new_prod_bg">
                        <a href="details.jsp"><img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>
                    
                     <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="category.productname"/></a>
                        <div class="new_prod_bg">
                        <a href="details.jsp"><img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>

                    <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="category.productname"/></a>
                        <div class="new_prod_bg">
                         <span class="new_icon"><img src="images/new_icon.gif" alt="" title="" /></span>
                        <a href="details.jsp"><img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>
                    <div class="new_prod_box">
                        <a href="details.jsp"><fmt:message key="category.productname"/></a>
                        <div class="new_prod_bg">
                        <a href="details.jsp"><img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>                  
                    

            <div class="pagination">
            <span class="disabled"></span><span class="current">1</span><a href="#?page=2">2</a><a href="#?page=3">3</a>…<a href="#?page=199">10</a><a href="#?page=200">11</a><a href="#?page=2">>></a>
            </div>  
            
            </div> 
          
            
        <div class="clear"></div>
        </div>
        
         <!-- right content-->
        
        
               <%@ include  file="right.jsp" %>
        
       
       
          <div class="clear"></div>
        </div>
  
        
       
       
       
       <div class="clear"></div>
  
       
       
       <!--end of center content-->
       
              

    
        <%@ include  file="footer.jsp" %>
