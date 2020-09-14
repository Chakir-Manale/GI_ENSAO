package TP1;

/**
 *ye9dar yew9a3ik wahed exception machi felcode mais dakchi dyal tomcat9 dyal les cookie
 *mohim ila srat ajourt� had la ligne lfichier   <===== Servers/tomcat.../context.xml
 *
 * <CookieProcessor className="org.apache.tomcat.util.http.LegacyCookieProcessor" />
 */




import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Formulaire extends HttpServlet {

	private String formulaire = "<html><head><title>Enregistrement  coordonn�es</title></head><body style='background:orange;'><h1>Enregistrement de vos coordonn�es</h1>"
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

			OldUser = cookies[1].getValue();

		}

		String more = "</body>" + "<h2>Vous �tes l utilisateur N� :" + count + "</h2>" + "<h3>Ancienne Utilisateur : "
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
			OldUser = cookies[1].getValue();
		
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
