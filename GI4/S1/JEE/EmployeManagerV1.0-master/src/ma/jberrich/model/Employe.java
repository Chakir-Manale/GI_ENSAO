package ma.jberrich.model;

public class Employe {
	private int id;
	private String nom;
	private int age;
	private String fonction;
	private int salaire;
	private Service service;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public int getSalaire() {
		return salaire;
	}
	
	public void setSalaire(int salaire) {
		this.salaire = salaire;
	}
	
	public Service getService() {
		return service;
	}
	
	public void setService(Service service) {
		this.service = service;
	}
}
