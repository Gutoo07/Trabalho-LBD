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

@Repository
public class ConsultaDao {
	
	private GenericDAO gDAO = new GenericDAO();

	public List<Consulta> getAllById() throws SQLException, ClassNotFoundException{
		Connection con = gDAO.getConnection();
		String sql = "SELECT * FROM consulta";
		PreparedStatement stm = con.prepareStatement(sql);
		
		
		List<Consulta> consultas = new ArrayList<Consulta>();
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			Consulta consulta = new Consulta();
			consulta.setId(rs.getInt("id"));
			//consulta.clienteRg = rs.getString("clienteRg");
			//consulta.medicoRg = rs.getString("medicoRg");
			consulta.setCod_autorizacao(rs.getString("cod_autorizacao"));
			consulta.setDia(rs.getString("dia"));
			consulta.setHora(rs.getString("hora"));
			consulta.setParticular(rs.getBoolean("particular"));
			consulta.setValor(rs.getDouble("valor"));
			consultas.add(consulta);

		}
		rs.close();
		stm.close();
		con.close();
	
		return consultas;
	}
	

	
}
