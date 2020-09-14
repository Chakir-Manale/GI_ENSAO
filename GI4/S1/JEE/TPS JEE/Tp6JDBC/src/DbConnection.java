import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	public static String url = "jdbc:mysql://localhost/employe?";
	public static String driverName = "com.mysql.cj.jdbc.Driver";
	public static String user = "root";
	public static String password = "root";
	public static Connection conn;

	public  static Connection getConnection() {
		try {
			
			Class.forName(driverName);
			try {

				conn = DriverManager.getConnection(
						url + "user=" + user + "&password=" + password + "&useSSL=false&serverTimezone=UTC");

			} catch (SQLException ex) {
				ex.printStackTrace();
				System.out.println("ERROR: failed to connect to MYSQL database.");

			}
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver not found.");
		}
		return conn;
    
    }
}
