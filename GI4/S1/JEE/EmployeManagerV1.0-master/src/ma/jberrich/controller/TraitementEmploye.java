package ma.jberrich.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.jberrich.dao.EmployeDAO;
import ma.jberrich.dao.IEmployeDAO;
import ma.jberrich.dao.IServiceDAO;
import ma.jberrich.dao.ServiceDAO;
import ma.jberrich.model.Employe;
import ma.jberrich.model.ListeEmployes;
import ma.jberrich.model.Service;

public class TraitementEmploye extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IEmployeDAO edao = new EmployeDAO();
	private IServiceDAO sdao = new ServiceDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomEmp = request.getParameter("nom");
		int ageEmp = Integer.parseInt(request.getParameter("age"));
		String fonctionEmp = request.getParameter("fonction");
		int salaireEmp = Integer.parseInt(request.getParameter("salaire"));
		String nomService = request.getParameter("service");
		
		Service service = sdao.getService(nomService);
		
		Employe employe = new Employe();
		employe.setNom(nomEmp);
		employe.setAge(ageEmp);
		employe.setFonction(fonctionEmp);
		employe.setSalaire(salaireEmp);
		employe.setService(service);
		
		edao.enregistrerEmploye(employe);
		
		ListeEmployes employes = new ListeEmployes();
		employes.setEmployes(edao.getListeEmployes());
		request.setAttribute("liste", employes);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/infoEmployes.jsp");
		dispatcher.forward(request, response);
	}

}
