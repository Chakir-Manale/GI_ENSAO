package com.ayoub.berramdane;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Formulaire
 */
@WebServlet("/Formulaire")
public class Formulaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int count ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Formulaire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()){
			out.println("<!Doctype html>");
			out.println("<html>");
			
			out.println("<head>");
			out.println("<meta charset='UTF-8'/> ");
			out.println("<title>Enregistrement Cordonnes</title>");
			out.println("<link rel='stylesheet' type='text/css' href='style.css'/>");
			out.println("</headl>");
			
			out.println("<body>");
			out.println("<h1>Enregistrement de votre coordonnee</h1>");
			out.println("<hr/>");
			out.println(" <form method='post' action='Formulaire' ");
			out.println(" <p>Civilité : <select name='elementSelectioner'> <option>Monsieur</option> <option>Madame</option> </select> </p> ");
			//out.println("<select> <option>Monsieur</option> <option>Madame</option> </select> ");
			//out.println("<option>Monsieur</option>");
			//out.println("<option>Madame</option>");
			//out.println("</select>");
			//out.println("</br>");
			out.println(" <p> Nom : <input name='nom' type='text' /> </p> ");
			out.println("<br/>");
			out.println(" Prenom : <input name='prenom' type='text' /> ");
			out.println("<br/>");
			out.println("<hr/>");
			out.println("<input name='btnC' type='submit' value='envoyer le resultat' />");
			out.println("<input type='reset' value='tout effacer' />");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String elementSelectioner = request.getParameter("elementSelectioner");
		
		
		response.setContentType("text/html");
		
		try (PrintWriter out = response.getWriter()){
			out.println("<!Doctype html>");
			out.println("<html>");
			
			out.println("<head>");
			out.println("<meta charset='UTF-8'/> ");
			out.println("<title>TP IHM 4éme année GI</title>");
			out.println("<link rel='stylesheet' type='text/css' href='style.css'/>");
			out.println("</headl>");
			
			out.println("<body>");
			out.println(" <h1>Enregitrement de vos coordonnes effectue</h1>");
			out.println("<hr/>");
			out.println("Bonjour" + "\n" + elementSelectioner + "\n" +nom + "\n" + prenom);
			out.println("</body>");
			out.println("</html>");
		}
		
	}

}
