<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<title>Pet Shop</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div id="wrap">

      <jsp:include page="header.jsp" />       
       
       <div class="center_content">
       	<div class="left_content">
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>My account</div>        
        	<div class="feat_prod_box_details">
            <p class="details">
             Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.
            </p>           
              	<div class="contact_form">
                <div class="form_subtitle">login into your account</div>
                <div id="error-message" class="error-login-message disabled">
                	<em>The username and password you entered did not match our records. Please double-check and try again.</em>
                </div>
                <form name="register" action="UserControllerServlet" method="post">          
                    <div class="form_row">
                    <label class="contact"><strong>Username:</strong></label>
                    <input  type="text" name="username" class="contact_input" />
                    </div>  
                    <div class="form_row">
                    <label class="contact"><strong>Password:</strong></label>
                    <input type="password" name="password" class="contact_input" />
                    </div>                     

                    <div class="form_row">
                        <div class="terms">
                        <input type="checkbox" name="terms" />
                        Remember me
                        </div>
                    </div>                    
                    <div class="form_row">
                    	<input type="submit" class="register" name="command" value="LOGIN" />
                    </div>                      
                  </form>     
                    
                </div>  
            
            </div>	            
        <div class="clear"></div>
        </div><!--end of left content-->
       <jsp:include page="right_content.jsp" />
               
       <div class="clear"></div>
       </div><!--end of center content-->
       
     <jsp:include page="footer.jsp" />
</div>
	<% 	
		if(session.getAttribute("currentSessionUser") != null && ("error".equals(session.getAttribute("currentSessionUser")))){
			out.print("<script>document.getElementById('error-message').classList.remove('disabled');</script>");
			session.removeAttribute("currentSessionUser");
		}
	%>
</body>
</html>