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
	
	/*SOLID: Responsabilidade unica: classe de persistencia especifica para Medico*/
	
	private GenericDAO gDAO;
	
	//SOLID: inversao de dependencia
	public MedicoDao(GenericDAO gDAO) {
		this.gDAO = gDAO;
	}

	public Medico getById(String rg) throws SQLException, ClassNotFoundException{
		Connection con = gDAO.getConnection();
		String sql = "SELECT * FROM medico WHERE rg = ?";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setString(1, rg);
		
		
		Medico medico = new Medico();
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			medico.setRg(rg);
			medico.setNome(rs.getString("nome"));
			medico.setTelefone(rs.getString("telefone"));
			medico.setPeriodo(rs.getString("periodo"));
			medico.setValor_consulta(Double.parseDouble(rs.getString("valor_consulta")));
			EspecialidadeDao eDao = new EspecialidadeDao(gDAO);
			Especialidade e = eDao.getById(rs.getInt("especialidade"));
			medico.setEspecialidade(e);
			medico.setSenha(rs.getString("senha"));
		}
		rs.close();
		stm.close();
		con.close();
	
		return medico;
	}
	public String sp_medico(String opc, Medico medico) throws SQLException, ClassNotFoundException {
		Connection con = gDAO.getConnection();
		String sql = "{CALL sp_medico(?,?,?,?,?,?,?,?,?)}";
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, opc);
		cs.setString(2, medico.getRg());
		cs.setString(3, medico.getNome());
		cs.setString(4, medico.getTelefone());
		cs.setString(5, medico.getPeriodo());
		cs.setDouble(6, medico.getValor_consulta());
		cs.setInt(7, medico.getEspecialidade().getId());
		cs.setString(8, medico.getSenha());
		cs.registerOutParameter(9, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(9);
		cs.close();
		con.close();
		return saida;
	}
	public String excluir(Medico medico) throws SQLException, ClassNotFoundException {
		Connection con = gDAO.getConnection();
		String sql = "{CALL sp_medico(?,?,?,?,?,?,?,?,?)}";
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, "D");
		cs.setString(2, medico.getRg());
		cs.setNull(3, Types.VARCHAR);
		cs.setNull(4, Types.VARCHAR);
		cs.setNull(5, Types.VARCHAR);
		cs.setNull(6, Types.DECIMAL);
		cs.setNull(7, Types.INTEGER);
		cs.setNull(8, Types.VARCHAR);
		cs.registerOutParameter(9, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(9);
		cs.close();
		con.close();
		return saida;
	}
	public List<Medico> getAll() throws SQLException, ClassNotFoundException {
		Connection con = gDAO.getConnection();
		String sql = "SELECT * FROM medico";
		PreparedStatement stm = con.prepareStatement(sql);
		
		List<Medico> lista = new ArrayList<>();
		ResultSet rs = stm.executeQuery();
		EspecialidadeDao eDao = new EspecialidadeDao(gDAO);
		while (rs.next()) {
			Medico m = new Medico();
			m.setRg(rs.getString("rg"));
			m.setNome(rs.getString("nome"));
			m.setTelefone(rs.getString("telefone"));
			m.setPeriodo(rs.getString("periodo"));
			m.setValor_consulta(rs.getDouble("valor_consulta"));
			Especialidade e = eDao.getById(rs.getInt("especialidade"));
			m.setEspecialidade(e);
			m.setSenha(rs.getString("senha"));
			lista.add(m);
		}
		rs.close();
		stm.close();
		con.close();
	
		return lista;
	}
}
