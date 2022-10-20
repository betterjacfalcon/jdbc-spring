package com.transtation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.h2.jdbc.JdbcSavepoint;

public class Transtation {

	public static void main(String[] args) throws SQLException {

		try(Connection  connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cure_illness", "postgres", "admin")){
		System.out.println("connected");
		connection.setAutoCommit(false);
		Savepoint savePoint = null;
		
		try {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO public.actividades_fisicas(descripcion) VALUES (?)");
		
		statement.setString(1, "traquilidad");
		statement.executeUpdate();
		
		savePoint = connection.setSavepoint("devs4j");

		statement.setString(1, "maraton");
		statement.executeUpdate();
		
		connection.releaseSavepoint(savePoint);
		
		statement.setString(1, "parapente");		
		statement.executeUpdate();
		
		connection.commit();
		}catch(SQLException e) {
			System.out.println("Rolling back bacause " + e.getMessage());
			if(savePoint==null) {
				connection.rollback();
			}else {
				connection.rollback(savePoint);
			}
		}finally{
			connection.setAutoCommit(true);
		}
		
		PreparedStatement statementQuery = connection.prepareStatement("SELECT * FROM public.actividades_fisicas ");
		ResultSet rs = statementQuery.executeQuery();
		
		while (rs.next()) {
			System.out.printf("\n Id = [%d] \tDescripcion = [%s]", rs.getInt(1), rs.getString(2));
		}
		
		}		
		
	}

}
