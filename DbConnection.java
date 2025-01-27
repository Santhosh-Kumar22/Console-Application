package com.theatre;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	// Database connection
	public static Connection getConnection(){
        Properties properties = new Properties();
        
        FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("src/com/theatre/jdbc.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        try {
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        // Extracting properties
        String dbUrl = properties.getProperty("db.url");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        String dbDriver = properties.getProperty("db.driver");

        // Load the database driver
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
