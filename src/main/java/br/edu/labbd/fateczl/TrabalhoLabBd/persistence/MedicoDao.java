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
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Medico;

@Repository
public class MedicoDao {
	
	private GenericDAO gDAO = new GenericDAO();

	public Medico getById(String rg) throws SQLException, ClassNotFoundException{
		Connection con = gDAO.getConnection();
		String sql = "SELECT * FROM medico WHERE rg = ?";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, rg);
		
		
		Medico medico = new Medico();
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			medico.setNome(rs.getString("nome"));
		}
		rs.close();
		stm.close();
		con.close();
	
		return medico;
	}
	

	
}
