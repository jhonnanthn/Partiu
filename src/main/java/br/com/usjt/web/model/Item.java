package br.com.usjt.web.model;

public class Item {

	private int id;
	private String cnpjRestaurante;
	private String categoria;
	private String nome;
	private String tipo;
	private double valor;
	
	private String detalhe;
	private String status;
	private String observacao;

	// Pedido
	private int idPedido;
	private String especialidade;
	private double porc_desconto;
	private String idComanda;
	private String data;
	private String statusPedido;

	// Usuario
	private int idUsuario;
	private String nomeUsuario;
	private String emailUsuario;
	private double porcPaga;
	private String statusPedidoUsuario;
	private double score;
	
	
	public String getEspecialidade() {
		return especialidade;
	}

	public double getScore() {
		return score;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public String getStatusPedidoUsuario() {
		return statusPedidoUsuario;
	}

	public void setStatusPedidoUsuario(String statusPedidoUsuario) {
		this.statusPedidoUsuario = statusPedidoUsuario;
	}

	public double getPorcPaga() {
		return porcPaga;
	}

	public void setPorcPaga(double porcPaga) {
		this.porcPaga = porcPaga;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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

	public String getCnpjRestaurante() {
		return cnpjRestaurante;
	}

	public void setCnpjRestaurante(String cnpjRestaurante) {
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

	public Item(int id, String cnpjRestaurante, String categoria, String nome, String tipo, double valor, String status,
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
