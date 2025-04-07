package br.edu.labbd.fateczl.TrabalhoLabBd.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import br.edu.labbd.fateczl.TrabalhoLabBd.model.LoginRequest;

@Repository
public class AuthDao {
	
	/*SOLID: Responsabilidade unica: classe de persistencia especifica para Login*/
	
	private GenericDAO gDAO = new GenericDAO();

	public int checkLogin(LoginRequest loginRequest) throws SQLException, ClassNotFoundException{
		
		Connection con = gDAO.getConnection();
		String sql = "{CALL sp_valida_login(?,?,?)}";
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, loginRequest.getRg());
		cs.setString(2, loginRequest.getPassword());
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
