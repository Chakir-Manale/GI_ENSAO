package petshop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Traitement {
	
	private static Connection connexion ;
	
//------------------------------------------------------------------------------------
		//se connecter a la BD 
	    public static Connection DBConnexion() throws SQLException
	    {

	       Connection conn=null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
	             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetShop" ,"root","");    
	           
	        } catch (ClassNotFoundException ex) {
	        		ex.printStackTrace(); }
	       return conn;
	    }

	    
//-----------------------------------------------------------------------	
	    public static void loadDataBase() {
		
	    try {
			
			 Class.forName("com.mysql.jdbc.Driver");
			 connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop", "root", "");
		
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//------------------------------------------------------------------------------------
	
	    public static Boolean verifyUser(String login,String pass) {
	     Boolean Valid=false;
	    	PreparedStatement pst=null;
	    	ResultSet rs=null;
	    	
	    	try{
	    	 loadDataBase();
	    	 
	         pst = connexion.prepareStatement("Select Username,Password from Utilisateur where Username=? and Password=?");
	         pst.setString(1, login);
	         pst.setString(2, pass);
	         
	         rs = pst.executeQuery();                        
	       if(rs.next()) {
	    	   Valid = true;
	       }
	            
	        
	   }
	   catch(Exception e){  
		   e.printStackTrace();
	       }
		finally {
			// close JDBC objects
			close(connexion, pst, rs);
		}
	    return Valid;
	    }
	    

 //------------------------------------------------------------------------------------		
	public static void addUser(Utilisateur user) {
			loadDataBase();
			try {
				PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO Utilisateur VALUES (?,?,?,?,?,?)");
				preparedStatement.setString(1, user.getUsername());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getEmail());
				preparedStatement.setInt(4, user.getPhone());
				preparedStatement.setString(5, user.getCompany());
				preparedStatement.setString(6, user.getAdresse());
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//------------------------------------------------------------------------------------
	    //rechercher un utilisateur dans la BDD
	    public static  Utilisateur SearchUser(String user) throws SQLException{
	    	loadDataBase();
			
	        java.sql.Statement st=connexion.createStatement();
	        java.sql.ResultSet res=st.executeQuery("select * from Utilisateur where Username='"+user+"' ");
	        Utilisateur p=new Utilisateur();
	        
	        while(res.next()){
		        p.setUsername(res.getString(1));
		        p.setPassword(res.getString(2));
		        p.setEmail(res.getString(3));
		        p.setPhone(res.getInt(4));
		        p.setCompany(res.getString(6));
		        p.setAdresse(res.getString(5));
		       
		        }
		        return p;
	    }

//------------------------------------------------------------------------------------
    
  //modifier un utilisateur 
	    public static void  modifier(String username,String password,String email,int phone,String company,String adresse) throws SQLException{
	      
	        Connection Cx=Traitement.DBConnexion();
	        java.sql.Statement st=Cx.createStatement();
	        int nbre=st.executeUpdate("update tab_personne set prenom='"+password+"',age='"+email+"',adresse='"+phone+"',ville='"+company+"',pays='"+adresse+"' where Username='"+username+"' ");
	        
	   }


//-------------------------------------------------------------------	    
 private static void close(Connection Conn, Statement Stmt, ResultSet Rs) {
		
		try {
			if(Rs != null) {
				Rs.close();
			}
			if(Stmt != null) {
				Rs.close();
			}
			if(Conn != null) {
				Rs.close(); // doesn't really close it ... just puts back in connection pool
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}
 
//-----------------------------  FIN ------------------------
}