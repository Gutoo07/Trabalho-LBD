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
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public LocalDate getDt_nasc() {
		return dt_nasc;
	}
	public void setDt_nasc(LocalDate dt_nasc) {
		this.dt_nasc = dt_nasc;
	}
	public String getDt_nascStr() {
		return dt_nascStr;
	}
	public void setDt_nascStr(String dt_nascStr) {
		this.dt_nascStr = dt_nascStr;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
