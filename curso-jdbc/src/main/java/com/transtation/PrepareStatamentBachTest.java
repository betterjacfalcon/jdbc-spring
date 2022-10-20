package com.transtation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.javafaker.Faker;

public class PrepareStatamentBachTest {

	public static void main(String[] args) {
		try(Connection  connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cure_illness", "postgres", "admin")){
			System.out.println("connected");
			connection.setAutoCommit(false);
			
			try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO public.actividades_fisicas(descripcion) VALUES (?)");
			Faker faker = new Faker();
			
			for(int i = 0; i< 100; i++) {
				statement.setString(1, faker.name().prefix());
				statement.addBatch();
				connection.setAutoCommit(true);
			}
			statement.executeBatch();
			int[] executeBach = statement.executeBatch();
			for(int i: executeBach) {
				System.out.println(i);
			}
 			}catch(SQLException e) {
 				System.out.println("Rolling back bacause " + e.getMessage());
 			}
			finally{
				connection.setAutoCommit(true);
			}			
			PreparedStatement statementQuery = connection.prepareStatement("SELECT * FROM public.actividades_fisicas ");
			ResultSet rs = statementQuery.executeQuery();
			
			while (rs.next()) {
				System.out.printf("\n Id = [%d] \tDescripcion = [%s]", rs.getInt(1), rs.getString(2));
			}
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

}
