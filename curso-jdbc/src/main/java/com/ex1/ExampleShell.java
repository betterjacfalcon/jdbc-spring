package com.ex1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;

public class ExampleShell {

	public static String readCommand() throws IOException {
		System.out.printf("\n shell->");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		return br.readLine();

	}

	public static void main(String[] args) throws SQLException, IOException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cure_illness", "postgres",
				"admin");
		String command = readCommand();

		while (!"quit".equals(command)) {
			try {
				Statement statement = connection.createStatement();
				boolean resultType = statement.execute(command);
				if (resultType) {
					ResultSet rs = statement.getResultSet();
					while (rs.next()) {
						ResultSetMetaData metaData = rs.getMetaData();
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							String value = rs.getString(i);
							System.out.print("\n" + value);
						}
						System.out.println();
					}
				} else {
					System.out.println("Row impacted: " + statement.getUpdateCount());
				}

			} catch (SQLException ex) {
					System.out.printf("\n Error %sexecuting statament %s", ex.getMessage(), command);
			}finally {
				command=readCommand();
			}
		}
		connection.close();
	}

}
