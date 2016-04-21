package com.Quartret;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnection() {
		try {
			String connectionURL = "jdbc:mysql://localhost:3306/quartethealth";
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "quartet", "sonali");
			return connection;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}