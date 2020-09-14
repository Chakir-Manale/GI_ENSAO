import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Connection con=null;
	private PreparedStatement preparedStmt = null;
	private ResultSet rs=null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String dbLogin,dbPassword;
	String login=request.getParameter("login");
	String password=request.getParameter("password");
	
	String selectQuery="select * from comptes where login = ? ";

		try {
			con = DbConnection.getConnection();
			
			preparedStmt=con.prepareStatement(selectQuery);
			preparedStmt.setString(1,login);
			
			rs=preparedStmt.executeQuery();
			
			if(rs!=null) {
				if(rs.next()) {
					
					dbLogin=rs.getString("login");
					dbPassword=rs.getString("password");
					
					if(dbPassword.equals(password) && dbLogin.equals(login)) {
						
						HttpSession session=request.getSession();
						session.setAttribute("id",rs.getString("id") );
						session.setAttribute("lib",rs.getString("lib"));
						session.setAttribute("solde",Double.parseDouble(rs.getString("solde")));
						request.setAttribute("session",session);
						response.sendRedirect("detail.jsp");
						
						
					}
					else {
						
						request.setAttribute("error",true);
						request.getRequestDispatcher("login.jsp").forward(request, response);;


					}
				}
				else {
					
					request.setAttribute("error",true);
					request.getRequestDispatcher("login.jsp").forward(request, response);;


				}
			}
			else {
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				
				if(rs!=null) rs.close();
				if(preparedStmt!=null) preparedStmt.close();
				if(con!=null) con.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		
		}
		
		 
	
	}

}
