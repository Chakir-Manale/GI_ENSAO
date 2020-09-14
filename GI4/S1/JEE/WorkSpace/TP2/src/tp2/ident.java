package tp1;


import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ident extends HttpServlet {

	private String formulaire = "<html><head><title>Enregistrement  coordonnées</title></head><body style='background:orange;'><h1>Enregistrement de vos coordonnées</h1>"
			+ "	<hr/>";

	private int count;

	public void init() {
		this.count = 0;
	}

	protected void getFormulaire(HttpServletRequest requst, HttpServletResponse response) throws IOException {
		Cookie[] cookies = requst.getCookies();
		count++;
		String OldUser = null;

		if (cookies != null) {

			OldUser = cookies[count].getValue();

		}

		String more = "</body>" + "<h2>Vous êtes l utilisateur N° :" + count + "</h2>" + "<h3>Ancienne Utilisateur : "
				+ OldUser + "</h3>" + "<h4>Bonjour Monsieur " + requst.getParameter("nom") + " "
				+ requst.getParameter("prenom") + "</h4>" + "</body>" + "</html>";

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println(count);
		out.println(formulaire + more);

	}

	protected void enregistrerUtilisateur(HttpServletRequest requst, HttpServletResponse response) throws IOException {
		String OldUser = null;

		Cookie[] cookies = requst.getCookies();
		if (cookies != null) {
			OldUser = cookies[count].getValue();
		
		}
		
		
		String user = requst.getParameter("nom") + " " + requst.getParameter("prenom");
		System.out.println(OldUser+ " "+ user);
		if (!user.equals(OldUser)) {
			Cookie data = new Cookie("Utilisateur", user);
			response.addCookie(data);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.enregistrerUtilisateur(req, resp);
		this.getFormulaire(req, resp);
		

	}

}
