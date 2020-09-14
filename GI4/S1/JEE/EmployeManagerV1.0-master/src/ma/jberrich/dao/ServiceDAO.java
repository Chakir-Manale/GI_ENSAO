package ma.jberrich.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.jberrich.model.Service;

public class ServiceDAO extends Database implements IServiceDAO {
		
	public ServiceDAO() {
		super();
	}

	private Service initService(ResultSet rs) throws SQLException {
		Service service = new Service();
		service.setId(rs.getInt("DEPTNO"));
		service.setNom(rs.getString("DNAME"));
		service.setLocal(rs.getString("LOC"));
		return service;
	}

	@Override	
	public Service getService(String nom) {
		Service service = null;
		try {
			String sql = "SELECT * FROM DEPT WHERE DNAME = ?";
			PreparedStatement pst = getConnexion().prepareStatement(sql);
			pst.setString(1, nom);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				service = initService(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return service;
	}
	
	@Override
	public Service getServiceByID(int id) {
		Service service = null;
		try {
			String sql = "SELECT * FROM DEPT WHERE DEPTNO = ?";
			PreparedStatement pst = getConnexion().prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				service = initService(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return service;
	}

	@Override
	public List<Service> getListeServices() {
		List<Service> services = new ArrayList<>();
		try {
			String sql = "SELECT * FROM DEPT";
			Statement stm = getConnexion().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				services.add(initService(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return services;
	}

}
