package br.edu.labbd.fateczl.TrabalhoLabBd.model;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginRequest {
	
	private String rg;
	private String password;
	
	public LoginRequest(String rg, String password) {
		this.rg = rg;
		this.password = password;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
