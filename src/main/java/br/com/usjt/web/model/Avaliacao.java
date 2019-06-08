package br.com.usjt.web.model;

public class Avaliacao {
	private int id;
	private String idCliente;
	private String cliente;
	private String idComanda;
	private String avEstabelecimento;
	private String avFuncionario;
	private String descEstabelecimento;
	private String descFuncionario;

	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public int getId() {
		return id;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public String getIdComanda() {
		return idComanda;
	}
	public String getAvEstabelecimento() {
		return avEstabelecimento;
	}
	public String getAvFuncionario() {
		return avFuncionario;
	}
	public String getDescEstabelecimento() {
		return descEstabelecimento;
	}
	public String getDescFuncionario() {
		return descFuncionario;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public void setIdComanda(String idComanda) {
		this.idComanda = idComanda;
	}
	public void setAvEstabelecimento(String avEstabelecimento) {
		this.avEstabelecimento = avEstabelecimento;
	}
	public void setAvFuncionario(String avFuncionario) {
		this.avFuncionario = avFuncionario;
	}
	public void setDescEstabelecimento(String descEstabelecimento) {
		this.descEstabelecimento = descEstabelecimento;
	}
	public void setDescFuncionario(String descFuncionario) {
		this.descFuncionario = descFuncionario;
	}
}
