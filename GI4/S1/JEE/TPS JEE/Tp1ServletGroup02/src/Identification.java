
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Identification
 */
public class Identification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int count;
	int track;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Identification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		String init = getInitParameter("count");
		this.count = Integer.parseInt(init);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		enregistrerUtilisateur(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String user = request.getParameter("user");
		String password = request.getParameter("pwd");
		Cookie c[] = request.getCookies();
		/*suppression des cookies deja enregistré dans le navigateur.
		 * for(int i=0;i<c.length;i++) {
		 *  c[i].setMaxAge(0);
		 *   response.addCookie(c[i]);
		 * out.println("cookie suprimé : "+c[i].getValue()); }
		 * out.print("\n length :"+c.length);
		 */
	
		
		String userNumber = "";
		boolean trouve=false;
		
		if (c != null) { 
			for (int i = 0; i < c.length; i++) 
			{
				c[i].setMaxAge(-1);
				response.addCookie(c[i]);
				
				
				if (c[i].getValue().equals(request.getParameter("user"))) 
				{
					userNumber = c[i].getName();
					trouve = true;
				}
			}
			if (!trouve) {
				userNumber = count + "";
				count++;

			}
		} else {
			userNumber = count + "";
			count++;
		}

		String output = "<!DOCTYPE html>" + "<html><head>" + "<title>Formulaire</title>"
				+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
				+ "</head>" + "<header>" + "<center><h2><em>Identification</em></h2></center>" + "</header><br>"
				// Tp2 : partie 1 +"<body><h4>Bonjour "+request.getParameter("user")+". Vous
				// êtes maintenant authentifié dans le système."+"<br>"
				+ "<body><h4>Bonjour " + request.getParameter("user") + ", Vous êtes l'utilisateur N : " + userNumber
				+ "</h4>"
				// +"Vous êtes l'utilisateur : "+count+"<br>"
				+ "</body></html>";
		
		out.println(output);
		
	}

	protected void enregistrerUtilisateur(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String user = request.getParameter("user") + " " + request.getParameter("password") + " "
				+ request.getParameter(count + "");

		response.addCookie(new Cookie(count + "", request.getParameter("user")));

	}
	protected void enregistrerUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		session.setAttribute(request.getParameter("user"),count+";"+request.getParameter("password"));
		
	}
	protected boolean ifUtilisateurExiste(HttpSession session,String user) {
		if(session.getAttribute(user)!=null) {
			return true;
		}
		else return false;
	}

}
