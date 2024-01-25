package back.backoffice_culture.Models;
import java.sql.*;

public class Connexion
{
	public static Connection getConnection()
	{
		try
		{
			Class.forName("org.postgresql.Driver");

			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/culture","postgres","root");

			return conn;
		}
		catch(Exception e){ 
			System.out.println(e);
		}
		return null;
	}
}