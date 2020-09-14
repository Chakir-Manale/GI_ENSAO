package ma.jberrich.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.aeonbits.owner.ConfigFactory;

public class Database {

	private Connection connexion;
	
	private String jdbcDriver;
	private String database;
	private String user;
	private String password;
	

	public Database() {
		connexionDB();
	}
	
	private void initConfiguration() {
		Configuration configuration = ConfigFactory.create(Configuration.class);
		jdbcDriver = configuration.getDriver();
		database = configuration.getUrl();
		user = configuration.getUsername();
		password = configuration.getPassword();
	}
	
	private void connexionDB() {
		try {
			initConfiguration();
			
			Class.forName(jdbcDriver).newInstance();
			
			connexion = DriverManager.getConnection(database, user, password);
		} catch (Exception e) {
			System.out.println("ERROR: failed to connect to MYSQL database.");
			e.printStackTrace();
		}
	}

	public Connection getConnexion() {
		return connexion;
	}
		
}
