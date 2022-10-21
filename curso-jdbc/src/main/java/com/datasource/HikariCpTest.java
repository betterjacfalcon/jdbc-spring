package com.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCpTest {

	public static void main(String[] args) throws SQLException{
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:postgresql://localhost:5432/cure_illness");
		config.setUsername("postgres");
		config.setPassword("admin");
		config.setMaximumPoolSize(5);
		HikariDataSource connectionPool =new HikariDataSource(config);
		Connection connection1 = connectionPool.getConnection();		
		Connection connection2 = connectionPool.getConnection();	
		Connection connection3 = connectionPool.getConnection();	
		Connection connection4 = connectionPool.getConnection();	
		Connection connection5 = connectionPool.getConnection();
		
		connection1.close();
		connection2.close();
		connection3.close();
		connection4.close();
		connection5.close();
		
		connectionPool.close();

	}

}
