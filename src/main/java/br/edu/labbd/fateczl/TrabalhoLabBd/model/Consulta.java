package br.edu.labbd.fateczl.TrabalhoLabBd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {	
	
	private int id;	
	private Cliente clienteRg;
	private Medico medicoRg;
	private String dia;
	private String hora;
	private boolean particular;	
	private double valor;
	private String cod_autorizacao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getClienteRg() {
		return clienteRg;
	}
	public void setClienteRg(Cliente clienteRg) {
		this.clienteRg = clienteRg;
	}
	public Medico getMedicoRg() {
		return medicoRg;
	}
	public void setMedicoRg(Medico medicoRg) {
		this.medicoRg = medicoRg;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public boolean isParticular() {
		return particular;
	}
	public void setParticular(boolean particular) {
		this.particular = particular;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getCod_autorizacao() {
		return cod_autorizacao;
	}
	public void setCod_autorizacao(String cod_autorizacao) {
		this.cod_autorizacao = cod_autorizacao;
	}
	

	
}
