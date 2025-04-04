package br.edu.labbd.fateczl.TrabalhoLabBd.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

@Repository
public class AuthDao {
	
	private GenericDAO gDAO = new GenericDAO();

	public int checkLogin(String rg, String password) throws SQLException, ClassNotFoundException{
		
		Connection con = gDAO.getConnection();
		String sql = "{CALL sp_valida_login(?,?,?)}";
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, rg);
		cs.setString(2, password);
		cs.registerOutParameter(3, Types.INTEGER);
		cs.execute();
		
		int saida = cs.getInt(3);
		
		cs.close();
		con.close();
	
		return saida;
	}
	
	public int checkConnection() {
	
		return 0;

	}
	
}
