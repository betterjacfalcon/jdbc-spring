package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCConection {

	public static void main(String[] args)  {
		try {
			System.out.println("Connecting");
			Connection  connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cure_illness", "postgres", "admin");
			
			PreparedStatement statement = connection.prepareStatement("INSERT INTO public.actividades_fisicas(descripcion) VALUES (?)");
			
			statement.setString(1, "saltar");
			
			PreparedStatement statementQuery = connection.prepareStatement("SELECT * FROM public.actividades_fisicas ");
			ResultSet rs = statementQuery.executeQuery();
			while (rs.next()) {
				System.out.printf("\n Id = [%d] \tDescripcion = [%s]", rs.getInt(1), rs.getString(2));
			}
					
			statementQuery.close();
			
			statement.executeUpdate();
			statement.close();
			System.out.println("Connected");
			System.out.println("Closing");
			connection.close();
			System.out.println("Closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
