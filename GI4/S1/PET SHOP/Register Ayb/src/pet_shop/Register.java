package pet_shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {
	//
	private Connection connexion ;
	
	
	
	//
	public void addUser(User user) {
		loadDataBase();
		try {
			PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO Utilisateur(UserName,Password,Email,Phone,Company,Adress) VALUES (?,?,?,?,?,?)");
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPhone());
			preparedStatement.setString(5, user.getCompany());
			preparedStatement.setString(6, user.getAdress());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadDataBase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			
		}
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:8889/petshop", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
