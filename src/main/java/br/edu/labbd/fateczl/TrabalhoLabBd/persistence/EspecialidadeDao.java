package br.edu.labbd.fateczl.TrabalhoLabBd.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.labbd.fateczl.TrabalhoLabBd.model.Consulta;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Especialidade;

@Repository
public class EspecialidadeDao {
	
	private GenericDAO gDAO = new GenericDAO();

	public Especialidade getById(String id) throws SQLException, ClassNotFoundException{
		Connection con = gDAO.getConnection();
		String sql = "SELECT * FROM especialidade";
		PreparedStatement stm = con.prepareStatement(sql);
		
		
		Especialidade especialidade = new Especialidade();
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			especialidade.setId(rs.getInt("id"));
			especialidade.setNome(rs.getString("nome"));

		}
		rs.close();
		stm.close();
		con.close();
	
		return especialidade;
	}
	

	
}
