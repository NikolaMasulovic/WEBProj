package ProjWEB.PROJWEB.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBContext {
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		String url = "jdbc:mysql://localhost/webProjDB";
		Class.forName("com.mysql.jdbc.Driver");
		Properties properties = new Properties();
		properties.setProperty("user", "root");
		properties.setProperty("password", "root");
		properties.setProperty("useSSL", "false");
		
		return DriverManager.getConnection(url,properties);
	}
}
