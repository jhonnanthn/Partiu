package br.com.usjt.web.model;

public class Item {

	private int id;
	private Usuario usuario;
	private long cnpjRestaurante;
	private double valor;
    private double porc_desconto;
	private String nome;
	private String categoria;
	private String status;
	private String tipo;
	private String idComanda;
    private String data;
    
	public Usuario getUsuario() {
		return usuario;
	}
	public double getPorc_desconto() {
		return porc_desconto;
	}
	public String getIdComanda() {
		return idComanda;
	}
	public String getData() {
		return data;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public void setPorc_desconto(double porc_desconto) {
		this.porc_desconto = porc_desconto;
	}
	public void setIdComanda(String idComanda) {
		this.idComanda = idComanda;
	}
	public void setData(String data) {
		this.data = data;
	}
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
