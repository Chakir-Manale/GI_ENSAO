package petshop;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
//------------------------------------------------- doGet --------------------------------------------------------------
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Requete = request.getParameter("Requete");
		HttpSession session=request.getSession(true);
		
		switch (Requete) {
		
			case "login":
				     login(request,response);
				break;
				
			case "register":
				register(request,response);
				
				break; 
				
			case "LogOut": 
				session.removeAttribute("utilisateur");
				 response.sendRedirect("index.jsp");  
			       	
			default:
				break;
		}
		
		
		
	}

	//------------------------------------------------------ doPost --------------------------------------------------
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	//------------------------------------------------------ Control Functions ---------------------------------------
	
	//----------------------------------LOGIN : 
	public void login(HttpServletRequest req, HttpServletResponse res) {
		 try {
		PrintWriter out= res.getWriter();
		HttpSession session=req.getSession(true);
		
	    String username = req.getParameter("Username");   
        String password = req.getParameter("Password");
        String rememberMe=  req.getParameter("rememberMe");
        
        //set the informations in the bean :
        Utilisateur u=new Utilisateur();
        u.setUsername(username);
        u.setPassword(password);
       
        if(username.isEmpty()|| password.isEmpty()) 
	    { 
        	session.setAttribute("error-emptyFields","please fill all the fields");
			res.sendRedirect("myaccount.jsp");
      
		 }
        else if(Traitement.verifyUser(username, password)) 
			    {
			     	  out.println("Valid login ");
			     	  session.setAttribute("utilisateur",username); 
			     	  
			     	//That means the checkBox RememberMe is checked ;) 
	        			if(rememberMe!=null)
	                    {
	                        Cookie usernameCookie = new Cookie("username-cookie", username);
	                        Cookie passwordCookie = new Cookie("password-cookie", password);
	                        usernameCookie.setMaxAge(24*60*60);
	                        passwordCookie.setMaxAge(24*60*60);
	                        res.addCookie(usernameCookie);
	                        res.addCookie(passwordCookie);
	                     }
	        			
			     	  res.sendRedirect("myaccount.jsp");  
			       	 
				      
				 }
		else if(!(Traitement.verifyUser(username, password)) )
		{
			session.setAttribute("error-InvalidLogin","Invalid login or password! please try again ");
				
        		
			     res.sendRedirect("myaccount.jsp");  
		}   	 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//------------------------------------------------------REGISTER : 
	public void register(HttpServletRequest req, HttpServletResponse res) {
		
		HttpSession session= req.getSession();
		try {
		
		Utilisateur utilisateur=new Utilisateur();
		 //validate given inputs
		if (req.getParameter("Username").isEmpty() || req.getParameter("Password").isEmpty() 
				|| req.getParameter("Email").isEmpty() ||req.getParameter("Phone").isEmpty() 
				||  req.getParameter("Company").isEmpty() ||  req.getParameter("Adresse").isEmpty())
		{
			session.setAttribute("error-emptyFields","please fill all the fields");
			res.sendRedirect("register.jsp");

		} else {
			 utilisateur.setUsername(req.getParameter("Username"));
		     utilisateur.setPassword(req.getParameter("Password"));
			 utilisateur.setEmail(req.getParameter("Email"));
			 utilisateur.setPhone(Integer.parseInt(req.getParameter("Phone")));
			 utilisateur.setCompany(req.getParameter("Company"));
			 utilisateur.setAdresse( req.getParameter("Adresse"));
				
		 Traitement.addUser(utilisateur);
		 res.sendRedirect("register.jsp");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
	
	
	}}
