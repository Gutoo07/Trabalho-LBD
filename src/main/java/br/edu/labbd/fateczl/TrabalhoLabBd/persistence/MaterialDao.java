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
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Material;

@Repository
public class MaterialDao {
	
	/*SOLID: Responsabilidade unica: classe de persistencia especifica para Material*/
	
	private GenericDAO gDAO;

	//SOLID: Inversao de Dependencia
	public MaterialDao(GenericDAO gDAO) {
		this.gDAO = gDAO;
	}

	
	public String inserir(Material m) throws SQLException, ClassNotFoundException {
		Connection con = gDAO.getConnection();
		String saida = "";
		try {
			String sql = "{CALL sp_material(?,?,?,?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, "I");
			cs.setInt(2, m.getId());
			cs.setString(3, m.getNome());
			cs.setDouble(4, m.getValor());
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			saida = cs.getString(5);
			cs.close();
		} catch(Exception error) {
			String sql = "{CALL sp_material(?,?,?,?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, "U");
			cs.setInt(2, m.getId());
			cs.setString(3, m.getNome());
			cs.setDouble(4, m.getValor());
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			saida = cs.getString(5);
			cs.close();
		} finally {
			con.close();
		}
		return saida;
	}

	
	public String deleteById(int id) throws SQLException, ClassNotFoundException {
	    Connection con = gDAO.getConnection();
	    String saida = "";

	    String sql = "{CALL sp_material(?, ?, ?, ?, ?)}";
	    CallableStatement cs = con.prepareCall(sql);
	    cs.setString(1, "D");
	    cs.setInt(2, id);
	    cs.setNull(3, Types.VARCHAR); 
	    cs.setNull(4, Types.DECIMAL);
	    cs.registerOutParameter(5, Types.VARCHAR);

	    try {
	        cs.execute();
	        saida = cs.getString(5);
	    } catch (SQLException e) {
	        throw e;
	    } finally {
	        cs.close();
	        con.close();
	    }

	    return saida;
	}


	
	public List<Material> getAll() throws SQLException, ClassNotFoundException {
		Connection con = gDAO.getConnection();
		String SQL = "SELECT * FROM material";
		PreparedStatement stm = con.prepareStatement(SQL);
		ResultSet rs = stm.executeQuery();
		
		List<Material> lista = new ArrayList<>();
		while (rs.next()) {
			Material material = new Material();
			material.setId(rs.getInt("id"));
			material.setNome(rs.getString("nome"));
			material.setValor(rs.getDouble("valor"));
			lista.add(material);
		}
		rs.close();
		stm.close();
		con.close();
		return lista;
	}

	
}