package com.kitchenstory.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection {

	private Connection connection = null;

	public DatabaseConnection() {
		// TODO Auto-generated constructor stub
	}

	DatabaseConnection(String url, String username, String password) throws SQLException {
		super();
		this.connection = DriverManager.getConnection(url, username, password);
	}

	private void printConnectionDetails() throws SQLException {
		System.out.println("Connected to database server " + this.connection.getMetaData().getDatabaseProductName()
				+ " version: " + this.connection.getMetaData().getDatabaseProductVersion() + "\n");
	}

	public Connection getConnection() {
		return connection;
	}
}

