package accredyApp.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {

	// These need to match the database. I used my login for now but we should have a separate "user" or "app" username so it's not just me.
	public final static String rdsMySqlDatabaseUrl = "mysql.wpi.edu";
	public final static String dbUsername = "individual";
	public final static String dbPassword = "7SAQMu";
		
	public final static String jdbcTag = "jdbc:mysql://";
	public final static String rdsMySqlDatabasePort = "3306";
	public final static String multiQueries = "?allowMultiQueries=true";
	   
	public final static String dbName = "cs542group4";

	// pooled across all usages.
	static Connection conn;
 
	/**
	 * Singleton access to DB connection to share resources effectively across multiple accesses.
	 */
	protected static Connection connect() throws Exception { //this was originally like this, so change it back later from public.
		if (conn != null) { return conn; }
		
		try {
			System.out.println("start connecting......");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					jdbcTag + rdsMySqlDatabaseUrl + ":" + rdsMySqlDatabasePort + "/" + dbName + multiQueries,
					dbUsername,
					dbPassword);
			System.out.println("Database has been connected successfully.");
			return conn;
		} catch (Exception ex) {
			throw new Exception("Failed in database connection");
		}
	}
}
