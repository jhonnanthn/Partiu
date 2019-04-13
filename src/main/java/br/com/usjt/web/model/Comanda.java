package br.com.usjt.web.model;

public class Comanda {

	private int id;
	private int codigo;
	private char status;
	private Usuario garcom;
	private String dtaEntrada;
	private String dtaSaida;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getGarcom() {
		return garcom;
	}

	public void setGarcom(Usuario garcom) {
		this.garcom = garcom;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getDtaEntrada() {
		return dtaEntrada;
	}

	public void setDtaEntrada(String dtaEntrada) {
		this.dtaEntrada = dtaEntrada;
	}

	public String getDtaSaida() {
		return dtaSaida;
	}

	public void setDtaSaida(String dtaSaida) {
		this.dtaSaida = dtaSaida;
	}
}
