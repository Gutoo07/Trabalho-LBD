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
	
	private GenericDAO gDAO;

	//SOLID: Inversao de Dependencia
	public ConsultaDao(GenericDAO gDAO) {
		this.gDAO = gDAO;
	}

	public List<Consulta> sp_ver_consultas(String rg) throws SQLException, ClassNotFoundException{
		Connection con = gDAO.getConnection();
		String sql = "{CALL sp_ver_consultas(?)}";
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, rg);		
		
		List<Consulta> consultas = new ArrayList<Consulta>();
		ResultSet rs = cs.executeQuery();
		while (rs.next()) {
			Consulta consulta = new Consulta();
			consulta.setId(rs.getInt("consulta_id"));

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
		cs.close();
		con.close();
	
		return consultas;
	}

	public String sp_consulta(String rg, int especialidade, String dia, String hora, 
			boolean particular, String codigo_autorizacao) throws SQLException, ClassNotFoundException{
		Connection con = gDAO.getConnection();
		String sql = "{CALL sp_consulta(?,?,?,?,?,?,?,?)}";
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, "I");
		cs.setString(2, rg);
		cs.setInt(3, especialidade);
		cs.setString(4, dia);
		cs.setString(5, hora);
		cs.setBoolean(6, particular);
		if (particular) {
			cs.setString(7, codigo_autorizacao);
		} else {
			cs.setNull(7, Types.VARCHAR);
		}
		cs.registerOutParameter(8, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(8);
		cs.close();
		con.close();
		return saida;
	}

	
}
