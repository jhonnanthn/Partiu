package br.com.usjt.web.model;

public class Item {

	private int id;
	private long cnpjRestaurante;
	private String categoria;
	private String nome;
	private String tipo;
	private double valor;
	private String status;

	// Pedido
	private int idPedido;
	private double porc_desconto;
	private String idComanda;
	private String data;
	private String statusPedido;

	// Usuario
	private int idUsuario;
	private String nomeUsuario;
	private String emailUsuario;
	
	public double getPorc_desconto() {
		return porc_desconto;
	}

	public String getIdComanda() {
		return idComanda;
	}

	public String getData() {
		return data;
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

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}

	
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public Item() {
		super();
	}

	public Item(int id, long cnpjRestaurante, String categoria, String nome, String tipo, double valor, String status,
			int idPedido, double porc_desconto, String idComanda, String data, String statusPedido, int idUsuario,
			String nomeUsuario, String emailUsuario) {
		super();
		this.id = id;
		this.cnpjRestaurante = cnpjRestaurante;
		this.categoria = categoria;
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
		this.status = status;
		this.idPedido = idPedido;
		this.porc_desconto = porc_desconto;
		this.idComanda = idComanda;
		this.data = data;
		this.statusPedido = statusPedido;
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
	}
	
	
	

}
