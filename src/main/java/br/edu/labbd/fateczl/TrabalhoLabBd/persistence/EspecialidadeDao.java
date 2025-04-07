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

@Repository
public class EspecialidadeDao {
	
	/*SOLID: Responsabilidade unica: classe de persistencia especifica para Especialidade*/
	
	private GenericDAO gDAO;

	//SOLID: Inversao de Dependencia
	public EspecialidadeDao(GenericDAO gDAO) {
		this.gDAO = gDAO;
	}

	
	public String inserir(Especialidade c) throws SQLException, ClassNotFoundException {
		Connection con = gDAO.getConnection();
		String saida = "";
		try {
			String sql = "{CALL sp_especialidade(?,?,?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, "I");
			cs.setInt(2,  c.getId());
			cs.setString(3, c.getNome());
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.execute();
			saida = cs.getString(4);
			cs.close();
		}catch(Exception error){
			String sql = "{CALL sp_especialidade(?,?,?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, "U");
			cs.setInt(2,  c.getId());
			cs.setString(3, c.getNome());
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.execute();
			saida = cs.getString(4);
		}finally{
			
			con.close();
		}
		
		return saida;
	}
	
	public String deleteById(int id) throws SQLException, ClassNotFoundException {
	    Connection con = gDAO.getConnection();
	    String saida = "";

	    String sql = "{CALL sp_especialidade(?, ?, ?, ?)}";
	    CallableStatement cs = con.prepareCall(sql);
	    cs.setString(1, "D");
	    cs.setInt(2, id);
	    cs.setNull(3, Types.VARCHAR);
	    cs.registerOutParameter(4, Types.VARCHAR);

	    try {
	        cs.execute();
	        saida = cs.getString(4);
	    } catch (SQLException e) {
	        throw e;
	    } finally {
	        cs.close();
	        con.close();
	    }

	    return saida;
	}

	
	public Especialidade getById(int id) throws SQLException, ClassNotFoundException{
		Connection con = gDAO.getConnection();
		String sql = "SELECT * FROM especialidade WHERE id = ?";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, id);
		
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
	public List<Especialidade> getAll() throws SQLException, ClassNotFoundException {
		Connection con = gDAO.getConnection();
		String SQL = "SELECT * FROM especialidade";
		PreparedStatement stm = con.prepareStatement(SQL);
		ResultSet rs = stm.executeQuery();
		
		List<Especialidade> lista = new ArrayList<>();
		while (rs.next()) {
			Especialidade especialidade = new Especialidade();
			especialidade.setId(rs.getInt("id"));
			especialidade.setNome(rs.getString("nome"));
			lista.add(especialidade);
		}
		rs.close();
		stm.close();
		con.close();
		return lista;
	}
	public Object getIdByNome(String nome)  throws SQLException, ClassNotFoundException {
		Connection con = gDAO.getConnection();
		String SQL = "SELECT id FROM especialidade WHERE nome = ?";
		PreparedStatement stm = con.prepareStatement(SQL);
		stm.setString(1, nome);
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			int especialidadeId = rs.getInt("id");
			rs.close();
			stm.close();
			con.close();
			return especialidadeId;
		}
		return null;
	}	
	public Especialidade getByNome(String nome)  throws SQLException, ClassNotFoundException {
		Connection con = gDAO.getConnection();
		String SQL = "SELECT * FROM especialidade WHERE nome = ?";
		PreparedStatement stm = con.prepareStatement(SQL);
		stm.setString(1, nome);
		ResultSet rs = stm.executeQuery();
		if (rs.next()) {
			Especialidade especialidade = new Especialidade();
			especialidade.setId(rs.getInt("id"));
			especialidade.setNome(rs.getString("nome"));
			rs.close();
			stm.close();
			con.close();
			return especialidade;
		}
		return null;	
	}
}