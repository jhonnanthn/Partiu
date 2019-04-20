package br.com.usjt.web.model;

public class Item {
	
	int id;
	long cnpjRestaurante;
	String nome;
	String categoria;
	String status;
	String tipo;
	double valor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getCnpjRestaurante() {
		return cnpjRestaurante;
	}
	public void setCnpjRestaurante(long cnpjRestaurante) {
		this.cnpjRestaurante = cnpjRestaurante;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public Item() {
		super();
	}
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", cnpjRestaurante=" + cnpjRestaurante + ", nome=" + nome + ", categoria=" + categoria
				+ ", status=" + status + ", tipo=" + tipo + ", valor=" + valor + "]";
	}

	
}
