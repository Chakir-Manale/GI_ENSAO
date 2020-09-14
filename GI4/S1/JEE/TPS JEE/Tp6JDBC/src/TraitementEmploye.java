

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TraitementEmploye extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String nom,fonction,nomService;
	int age,salaire;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 age=Integer.parseInt(request.getParameter("age"));
		 salaire=Integer.parseInt(request.getParameter("salaire"));
		 nom=request.getParameter("nom");
		 nomService=request.getParameter("nomService");
		 fonction=request.getParameter("fonction");
		 
		 Employe emp=new Employe(nom,age,fonction,salaire,nomService);
		 
		 HttpSession session=request.getSession();
		 session.setAttribute("employe",emp);
		 response.sendRedirect("infoEmploye.jsp");
	
	}

}
