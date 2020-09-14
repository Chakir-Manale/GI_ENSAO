package ma.petshop;

import java.io.Serializable;

public class User implements Serializable {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String company;
	private String adrres;
	public User(int id, String username, String password, String email, String phone, String company, String adrres) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.company = company;
		this.adrres = adrres;
	}
	public User(String username, String password, String email, String phone, String company, String adrres) {
		super();
		
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.company = company;
		this.adrres = adrres;
	}
	public User() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAdrres() {
		return adrres;
	}
	public void setAdrres(String adrres) {
		this.adrres = adrres;
	}
}
