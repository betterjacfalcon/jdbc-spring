package com.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class dbcp2 {
	private static final int NEW_CONNECTIONS = 10000;

	public static void main(String[] args) throws SQLException {
		BasicDataSource connectionPool=new 	BasicDataSource();
		connectionPool.setUrl("jdbc:postgresql://localhost:5432/cure_illness");		
		connectionPool.setUsername("postgres");
		connectionPool.setPassword("admin");	
		
		long startTime = System.currentTimeMillis();
		for(int i = 0; i< NEW_CONNECTIONS; i++) {
			Connection connection = connectionPool.getConnection();			
			connection.close();
		}
		connectionPool.close();
		System.out.println("Total Time= "+ (System.currentTimeMillis() - startTime  + " ms"));

	}
}
