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
	
	private int bayesEstabelecimento;
	private int bayesFuncionario;
	
	

	@Override
	public String toString() {
		return "Avaliacao [id=" + id + ", idCliente=" + idCliente + ", cliente=" + cliente + ", idComanda=" + idComanda
				+ ", avEstabelecimento=" + avEstabelecimento + ", avFuncionario=" + avFuncionario
				+ ", descEstabelecimento=" + descEstabelecimento + ", descFuncionario=" + descFuncionario
				+ ", bayesEstabelecimento=" + bayesEstabelecimento + ", bayesFuncionario=" + bayesFuncionario + "]";
	}
	public int getBayesEstabelecimento() {
		return bayesEstabelecimento;
	}
	public void setBayesEstabelecimento(int bayesEstabelecimento) {
		this.bayesEstabelecimento = bayesEstabelecimento;
	}
	public int getBayesFuncionario() {
		return bayesFuncionario;
	}
	public void setBayesFuncionario(int bayesFuncionario) {
		this.bayesFuncionario = bayesFuncionario;
	}
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
