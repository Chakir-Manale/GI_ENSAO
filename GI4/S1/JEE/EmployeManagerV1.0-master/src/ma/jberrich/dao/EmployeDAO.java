package ma.jberrich.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.jberrich.model.Employe;

public class EmployeDAO extends Database implements IEmployeDAO {
		
	private IServiceDAO sdao = new ServiceDAO();
	
	public EmployeDAO() {
		super();
	}

	@Override
	public void enregistrerEmploye(Employe emp) {
		try {
			String sql = "INSERT INTO EMP(ENAME, JOB, AGE, SAL, DEPTNO) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement pst = getConnexion().prepareStatement(sql);
			pst.setString(1, emp.getNom());
			pst.setString(2, emp.getFonction());
			pst.setInt(3, emp.getAge());
			pst.setInt(4, emp.getSalaire());
			pst.setInt(5, emp.getService().getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Employe initEmploye(ResultSet rs) throws SQLException {
		Employe employe = new Employe();
		employe.setId(rs.getInt("EMPNO"));
		employe.setNom(rs.getString("ENAME"));
		employe.setFonction(rs.getString("JOB"));
		employe.setAge(rs.getInt("AGE"));
		employe.setSalaire(rs.getInt("SAL"));
		employe.setService(sdao.getServiceByID(rs.getInt("DEPTNO")));
		return employe;
	}

	@Override
	public List<Employe> getListeEmployes() {
		List<Employe> employes = new ArrayList<>();
		try {
			String sql = "SELECT * FROM EMP";
			Statement stm = getConnexion().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				employes.add(initEmploye(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employes;
	}

}
