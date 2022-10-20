package com.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;
import org.postgresql.ds.PGSimpleDataSource;

public class DatasourceTest {

	public static void main(String[] args) throws SQLException {
		PGSimpleDataSource dataSource = new PGSimpleDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/cure_illness");
		dataSource.setUser("postgres");
		dataSource.setPassword("admin");
		
		try (Connection connection = dataSource.getConnection()){
			
		}
	}

}
