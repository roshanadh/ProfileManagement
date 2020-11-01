package np.com.roshanadhikary.profilems.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class DbUtil {
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String DATABASE_NAME =
			System.getenv("DATABASE_NAME") == null
			? "profilems"
			: System.getenv("DATABASE_NAME");
				
	private static final String URL =
			System.getenv("JDBC_URL") == null
			? "jdbc:mysql://localhost:3306/"
			: System.getenv("JDBC_URL");
	
	private static final String USERNAME =
			System.getenv("JDBC_USER") == null
			? "root"
			: System.getenv("JDBC_USER");
	
	private static final String PASSWORD = 
			System.getenv("JDBC_PASS") == null
			? ""
			: System.getenv("JDBC_PASS");
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER_NAME);
		return DriverManager.getConnection(URL + DATABASE_NAME, USERNAME, PASSWORD);
	}
}
