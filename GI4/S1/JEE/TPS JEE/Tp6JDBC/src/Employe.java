
public class Employe {
private String nom;
private int age;
private String fonction;
private int salaire;
private String nomService;
public Employe() {
	
}
public Employe(String nom, int age, String fonction, int salaire, String nomService) {
	super();
	this.nom = nom;
	this.age = age;
	this.fonction = fonction;
	this.salaire = salaire;
	this.nomService = nomService;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getFonction() {
	return fonction;
}
public void setFonction(String fonction) {
	this.fonction = fonction;
}
public  int getSalaire() {
	return salaire;
}
public void setSalaire(int salaire) {
	this.salaire = salaire;
}
public String getNomService() {
	return nomService;
}
public void setNomService(String nomService) {
	this.nomService = nomService;
}

}
