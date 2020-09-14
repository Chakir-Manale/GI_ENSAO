package petshop;
/* Class Bean : Utilisateur
 *  contient juste les attribut concernant l'utilisateur + getters&setters 
 */


public class Utilisateur {

	//attributs
	     String username;
	     String password;
	     String email;
	     int phone;
	     String company;
	     String adresse;
	     
	//Constructeur
	    public Utilisateur(){}

	//getters && setters 
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

		public int getPhone() {
			return phone;
		}

		public void setPhone(int phone) {
			this.phone = phone;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

	  
	
}
