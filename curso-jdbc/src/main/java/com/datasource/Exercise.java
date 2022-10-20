package com.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcConnectionPool;

public class Exercise {

	private static final int NEW_CONNECTIONS = 100000;

	public static void main(String[] args) throws SQLException {
		JdbcConnectionPool  connectionPool = JdbcConnectionPool.create("jdbc:h2:~/test", "postgres", "admin");

		long startTime = System.currentTimeMillis();
		for(int i = 0; i< NEW_CONNECTIONS; i++) {
			Connection connection = connectionPool.getConnection();
			
			
			connection.close();
		}
		System.out.println("Total Time= "+ ((System.currentTimeMillis() - startTime)/1000  + " s"));

	}

}
