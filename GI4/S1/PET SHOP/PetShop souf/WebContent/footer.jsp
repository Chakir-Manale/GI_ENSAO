<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}" scope="session"/>
     <fmt:setLocale value="${theLocale}"/>
     <fmt:setBundle basename="com.example.i18n.resources.mylabels"/>
  <div class="footer">
       	<div class="left_footer"><img src="images/footer_logo.gif" alt="" title="" /><br /> <a href="http://csscreme.com/freecsstemplates/" title="free css templates"><img src="images/csscreme.gif" alt="free css templates" border="0" /></a></div>
        <div class="right_footer">
        <a href="#"><fmt:message key="label.home"/></a>
        <a href="#"><fmt:message key="label.aboutUs"/></a>
        <a href="#"><fmt:message key="label.services"/></a>
        <a href="#"><fmt:message key="label.privacyPolicy"/></a>
        <a href="#"><fmt:message key="label.contactUs"/></a>
            
        </div>
        
       
       </div>
    
