package pet_shop;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("Username"));
		user.setPassword(request.getParameter("Password"));
		user.setEmail(request.getParameter("Email"));
		user.setPhone(request.getParameter("Phone"));
		user.setCompany(request.getParameter("Company"));
		user.setAdress(request.getParameter("Adress"));
		
		Register register = new Register();
		register.addUser(user);
		this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		//
		//
		
		
	}

}
