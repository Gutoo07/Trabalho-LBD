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

import br.edu.labbd.fateczl.TrabalhoLabBd.model.Cliente;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Consulta;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Especialidade;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Medico;

@Repository
public class ConsultaDao {
	
	private GenericDAO gDAO = new GenericDAO();

	public List<Consulta> getAllById(String rg) throws SQLException, ClassNotFoundException{
		Connection con = gDAO.getConnection();
		String sql = "SELECT \r\n"
				+ "	c.*,\r\n"
				+ "	m.nome as nome_medico,\r\n"
				+ "	m.rg as rg_medico,\r\n"
				+ "	m.telefone as telefone_medico,\r\n"
				+ "	cl.nome as nome_cliente,\r\n"
				+ "	cl.rg as rg_cliente,\r\n"
				+ "	cl.telefone as telefone_cliente,\r\n"
				+ "	e.id as id_especialidade,\r\n"
				+ "	e.nome as nome_especialidade\r\n"
				+ "FROM consulta c\r\n"
				+ "JOIN medico m ON m.rg = c.medicoRg\r\n"
				+ "JOIN cliente cl on cl.rg = c.clienteRg\r\n"
				+ "JOIN especialidade e on e.id = m.especialidade\r\n"
				+ "WHERE c.clienteRg = ? or c.medicoRg = ?";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, rg);
		stm.setString(2, rg);
		
		
		
		List<Consulta> consultas = new ArrayList<Consulta>();
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			Consulta consulta = new Consulta();
			consulta.setId(rs.getInt("id"));

			Cliente cliente = new Cliente();
			cliente.setNome(rs.getString("nome_cliente"));
			consulta.setClienteRg(cliente);
			
			Especialidade especialidade = new Especialidade();
			especialidade.setNome(rs.getString("nome_especialidade"));
			
			Medico medico = new Medico();
			medico.setNome(rs.getString("nome_medico"));
			medico.setEspecialidade(especialidade);
			consulta.setMedicoRg(medico);
			
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
