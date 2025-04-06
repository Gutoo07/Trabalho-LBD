package br.edu.labbd.fateczl.TrabalhoLabBd.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class GenericDAO {

	private Connection c;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		String hostName = "localhost";
		String dbName = "LabBD";
		String user = "kevin";
		String senha = "123456789";
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		c = DriverManager.getConnection(String.format(
				"jdbc:jtds:sqlserver://%s:57480;databaseName=%s;user=%s;password=%s;", hostName, dbName, user, senha ));
		return c;
	}
}
