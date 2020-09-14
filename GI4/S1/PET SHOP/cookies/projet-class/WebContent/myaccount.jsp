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
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>My account</div>
        
        	<div class="feat_prod_box_details">
            <p class="details">
             Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.
            </p>
                            <%
    Cookie[] cookies = request.getCookies();
    String username="";
    String password = "";
    if(cookies!=null)
    {
      for(int i=0;i<cookies.length;i++){
        Cookie cookie = cookies[i];
        if(cookie.getName().equals("username-cookie"))
        {
            username= cookie.getValue();
        }
        else if(cookie.getName().equals("password-cookie"))
        {
            password= cookie.getValue();
        }
      }
    }

   %>
              	<div class="contact_form">
                <div class="form_subtitle">login into your account</div>

                 <form name="register" action="index.jsp" method="POST">          
                    <div class="form_row">
                    <label class="contact"><strong>Username:</strong></label>
                    
                   <input type="text" name="username"  placeholder="username" value ="<%= username %>"  class="contact_input"  /> 
                   
                    </div>  


                    <div class="form_row">
                    <label class="contact"><strong>Password:</strong></label>
                   
                    <input type="password" name="password" placeholder="password" value="<%= password %>" class="contact_input"/>
                    </div>                     

                    <div class="form_row">
                        <div class="terms">
                        
                        <input type="checkbox" name="rememberMe" value ="true"/>
                        Remember me
                        </div>
                    </div> 

                    
                    <div class="form_row">
                    <input type="submit" class="register" value="login" />
                    </div>   
                    
                  </form>     
                    
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
</div>

</body>
</html>