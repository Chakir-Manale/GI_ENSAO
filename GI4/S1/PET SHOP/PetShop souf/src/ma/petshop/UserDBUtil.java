package ma.petshop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public class UserDBUtil implements IUserDBUtil {
	private DataSource dataSource;
	
	public UserDBUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean checkUser(User user) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		boolean userExist = false;
		
		try {

			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "SELECT username, password FROM users WHERE username = '" + user.getUsername() + "'";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			if(myRs.next() 
					&& (myRs.getString("username").toLowerCase().equals(user.getUsername().toLowerCase()))
				    && (myRs.getString("password").equals(user.getPassword()))
				    ) 
			{
				userExist = true;
			}
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
		return userExist;
	}

	@Override
	public void InsertUser(String username, String password, String email, int phone, String company, String adress) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {

			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "INSERT INTO `users` ("
					+ "`userName`, `password`, `email`, `phone`, `company`, `adress`)"
					+ " VALUES (" + username + ", " + password + ", " + email + ", " + phone + ", " + company + ", " + adress + ");";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myStmt.executeQuery(sql);
		
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			if(myRs != null) {
				myRs.close();
			}
			if(myStmt != null) {
				myRs.close();
			}
			if(myConn != null) {
				myRs.close(); // doesn't really close it ... just puts back in connection pool
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}

	@Override
	public void delUser(String username, String password, String email, int phone, String company, String adress) {
		// TODO Auto-generated method stub
		
	}
}
