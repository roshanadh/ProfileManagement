package np.com.roshanadhikary.profilems.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class DbUtil {
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String DATABASE_NAME = "profilems";
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER_NAME);
		return DriverManager.getConnection(URL + DATABASE_NAME, USERNAME, PASSWORD);
	}
}
