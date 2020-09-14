package ma.petshop;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDBUtil userDBUtil;
    
	@Resource(name = "jdbc/petshopp")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		
		// create our user db util ... and pass in the conn pool / database
		try {
			userDBUtil = new UserDBUtil(dataSource);
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get the command parameter
		String theCommand = request.getParameter("command");
		
		switch(theCommand) {
			case "LOGIN":
				login(request, response);
				break;
			case "SIGNIN":
				signIn(request, response);
				break;
			case "LOGOUT":
				logOut(request, response);
				break;
			default:
				//TODO redirect to an error page
				;
		}
	}

	private void logOut(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void signIn(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password"); 
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String company = request.getParameter("company");
		String adress = request.getParameter("adress");

		//TODO do sth about checkbox 
		
		User user = new User(username, password, email, phone, company, adress);
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

        HttpSession session = request.getSession(true);	    

        try {
        	
        	if(userDBUtil.checkUser(user)) {
    			//session.setAttribute("currentSessionUser",user.getUsername()); 
        		session.setAttribute("currentSessionUser",user); 
    			response.sendRedirect("index.jsp");
    		} else {
    			// en cas d'erreur
    			session.setAttribute("currentSessionUser","error"); 
    	        response.sendRedirect("myaccount.jsp");
    		}
        	
        }catch(Exception exc) {
        	exc.printStackTrace();
        }		
	}
}
