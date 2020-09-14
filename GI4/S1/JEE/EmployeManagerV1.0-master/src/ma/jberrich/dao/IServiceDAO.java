package ma.jberrich.dao;

import java.util.List;

import ma.jberrich.model.Service;

public interface IServiceDAO {

	public Service getService(String s);
	public Service getServiceByID(int id);
	public List<Service> getListeServices();
	
}
