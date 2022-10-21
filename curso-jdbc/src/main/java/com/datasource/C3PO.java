package com.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3PO {
	private static final int NEW_CONNECTIONS = 10000;

	public static void main(String[] args) throws SQLException {
		ComboPooledDataSource connectionPool = new ComboPooledDataSource();
		connectionPool.setJdbcUrl("jdbc:postgresql://localhost:5432/cure_illness");
		connectionPool.setUser("postgres");
		connectionPool.setPassword("admin");

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < NEW_CONNECTIONS; i++) {
			Connection connection = connectionPool.getConnection();
			connection.close();
		}
		connectionPool.close();
		System.out.println("Total Time= " + (System.currentTimeMillis() - startTime + " ms"));

	}
}
