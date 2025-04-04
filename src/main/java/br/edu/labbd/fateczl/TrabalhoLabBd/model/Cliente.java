package br.edu.labbd.fateczl.TrabalhoLabBd.model;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

	private String rg;
	private String nome;
	private String telefone;
	private LocalDate dt_nasc;
	private String dt_nascStr;
	private String senha;
	
	public String getRg() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getTelefone() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getDt_nasc() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getSenha() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setNome(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setTelefone(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setDt_nasc(LocalDate localDate) {
		// TODO Auto-generated method stub
		
	}
	public void setSenha(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setRg(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setDt_nascStr(String string) {
		// TODO Auto-generated method stub
		
	}
}
