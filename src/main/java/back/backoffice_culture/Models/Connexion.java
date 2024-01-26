package back.backoffice_culture.Models;
import java.sql.*;

public class Connexion
{
	public static Connection getConnection()
	{
		try
		{
			Class.forName("org.postgresql.Driver");

			//Connection conn = DriverManager.getConnection("jdbc:postgresql://viaduct.proxy.rlwy.net:54658/railway","postgres","G5d--2dEg5g5B1fag*Gc*1b-4B3Dfa2C");

			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Culture", "postgres", "root");

			return conn;
		}
		catch(Exception e){ 
			System.out.println(e);
		}
		return null;
	}
}