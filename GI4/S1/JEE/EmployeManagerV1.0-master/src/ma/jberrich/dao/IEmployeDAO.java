package ma.jberrich.dao;

import java.util.List;

import ma.jberrich.model.Employe;

public interface IEmployeDAO {

	public void enregistrerEmploye(Employe e);
	public List<Employe> getListeEmployes();
	
}
