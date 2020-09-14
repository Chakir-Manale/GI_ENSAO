package ma.petshop;

public interface IUserDBUtil {
	
	public boolean checkUser(User user);
	public void InsertUser( String username, String password, String email, int phone, String company, String adress);
	public void delUser( String username, String password, String email, int phone, String company, String adress);
}
