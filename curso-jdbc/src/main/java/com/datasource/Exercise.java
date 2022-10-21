package com.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Exercise {

	private static final int NEW_CONNECTIONS = 10000;

	public static void main(String[] args) throws SQLException {
		//JdbcConnectionPool  connectionPool = JdbcConnectionPool.create("jdbc:h2:~/test", "postgres", "admin");
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:postgresql://localhost:5432/cure_illness");
		config.setUsername("postgres");
		config.setPassword("admin");
		HikariDataSource connectionPool =new HikariDataSource(config);
		long startTime = System.currentTimeMillis();
		for(int i = 0; i< NEW_CONNECTIONS; i++) {
			Connection connection = connectionPool.getConnection();			
			connection.close();
		}
		connectionPool.close();
		System.out.println("Total Time= "+ (System.currentTimeMillis() - startTime  + " ms"));

	}

}
