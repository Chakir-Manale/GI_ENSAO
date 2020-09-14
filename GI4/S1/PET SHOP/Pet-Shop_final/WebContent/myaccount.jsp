   <%@ page import="petshop.*" %>
   <%@ page import="java.sql.*" %>
    <jsp:useBean id="utilisateur" class="petshop.Utilisateur"></jsp:useBean>
        
  
      <!-- including the header  -->
	   <jsp:include page="header.jsp"/> 
      

       
       <div class="center_content">
       
                 
              <%
              			HttpSession Sess=request.getSession();
             			String  user=(String)Sess.getAttribute("utilisateur");
		 	   	  
	        if(user!=null){   %> 
				 	  
				 	    <jsp:include  page="Session-On.jsp" >
				 	   	 	<jsp:param value="<%=user %>" name="User"/>
				 	    </jsp:include > 
				   
	  		 <%  }else{ 
	  		       	out.print("<script>document.getElementById('error-message').classList.remove('disabled');</script>");
					session.removeAttribute("utilisateur");
		     %>
	  			 <%@ include  file="Session-off.jsp" %>
	  		 <% }  %>
        <!--end of left content-->
        
		<!--  including the right side  -->
               <%@ include  file="right.jsp" %>
        
        <!--end of right content-->
       
 	 	<div class="clear"></div>
 	 	
 	 	
   </div> <!--end of center content-->
   
   
      
	  <%@ include  file="footer.jsp" %> <!--  including the footer   -->
