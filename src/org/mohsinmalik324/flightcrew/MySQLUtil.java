package org.mohsinmalik324.flightcrew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLUtil {
	
	private static final String USER = "sql9208791";
	private static final String PASS = "HALlmFZxtp";
	private static final String DB = "sql9208791";
	private static final String HOST = "sql9.freemysqlhosting.net";
	private static final int PORT = 3306;
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB, USER, PASS);
	}
	
	public static void closeConnection(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}