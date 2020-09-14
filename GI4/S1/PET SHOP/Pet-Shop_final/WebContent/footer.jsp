<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	

<c:if test="${!empty param.theLocale }">
<c:set var="theLocale" 
value="${param.theLocale}" scope="session" />
</c:if>

<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="petshop.i18n.translate.mylabels" />


<!--end of center content-->
       
              
       <div class="footer">
       	<div class="left_footer"><img src="images/footer_logo.gif" alt="" title="" /><br /> <a href="http://csscreme.com/freecsstemplates/" title="free css templates"><img src="images/csscreme.gif" alt="free css templates" border="0" /></a></div>
        <div class="right_footer">
        <a href="#"><fmt:message key="footer.home"/></a>
        <a href="#"><fmt:message key="footer.aboutus"/></a>
        <a href="#"><fmt:message key="footer.services"/></a>
        <a href="#"><fmt:message key="footer.privacypolicy"/></a>
        <a href="#"><fmt:message key="footer.contactus"/></a>
       
        </div>
        
       
       </div>
    

</body>
</html>