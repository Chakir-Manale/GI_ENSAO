import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Formulaire
 */
public class Formulaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Formulaire() {
        super();
        // TODO Auto-generated constructor stub
    }
   
    public void init()  {
    	String init=getInitParameter("count");
    	count=Integer.parseInt(init);
    
    	
    }
    public void getFormulaire(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("text/html");
    	PrintWriter out=response.getWriter();
    	String civilite=request.getParameter("civilite");
    	String nom=request.getParameter("nom");
    	String prenom=request.getParameter("prenom");
    	 Cookie c[];
    	count++;
    	c=request.getCookies();
    	String oldUser="";
    	if(c!=null){
    		oldUser=c[0].getValue();   
    	}
    	else {
    		oldUser="Aucun";
    	}/*
    	for(int i=0;i<c.length;i++) {
    		oldUser+=c[i].getValue()+" / ";
    	}*/
    	String output="<!DOCTYPE html>"
    			+ "<html><head>" + 
    			"<title>Formulaire</title>" + 
    			"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
    			+"</head>"
    			+"<header>" 
    			+"<center><h2><em>Enregistrement de vos coordonnées</em></h2></center>"
    			+"</header><br><br>"
    			+"<body><h4>Bonjour "+civilite+" "+nom+" "+prenom+"<br>"
    			+"Vous êtes l'utilisateur : "+count+"<br>"
    			+"Utilisateur précedent : "+oldUser+"</h4>"
    			+"</body></html>";
    	out.println(output);
    //	afficherUtilisateur(out);
    	

    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public int count;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*enregistrerUtilisateur(request,response);
		getFormulaire(request,response);*/
		
		
	}
	protected void enregistrerUtilisateur(HttpServletRequest request, HttpServletResponse response) throws IOException{
	
		String user=request.getParameter("civilite")+" "+request.getParameter("nom")+" "+request.getParameter("prenom");
		response.addCookie(new Cookie("user",user));
	
		
		
	}
	/*protected void afficherUtilisateur(PrintWriter out) {
		String output="";
		
		
		if(c!=null) {
			for(int i=0;i<c.length;i++) {
				output+=c[i].getValue()+" \n ";
			}
		}
		else {
			output+="Vide";
		}
		out.print(output);
		
		
	}*/

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		enregistrerUtilisateur(request,response);
		getFormulaire(request,response);
	}

}
