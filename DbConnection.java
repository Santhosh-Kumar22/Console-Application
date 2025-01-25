package com.theatre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	static final String URL = "jdbc:mysql://localhost:3306/theatredb";
	static final String user = "root";
	static final String password = "Aspire@123";
	
	
	// Database connection
	public static Connection getConnection() throws SQLException{
		Connection connection = DriverManager.getConnection(URL, user, password);
		return connection;
	}
}
