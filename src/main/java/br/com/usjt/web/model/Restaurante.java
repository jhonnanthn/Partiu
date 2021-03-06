package br.com.usjt.web.model;

public class Restaurante {
	
	private String cnpj;
	private double avaliacao;
	private int dia;
	private int qtdMesas;
	private String codigoComanda;
	private String razaoSocial;
	private String nomeFantasia;
	private String status;
	private String logo;
	private String descricao;
	private String hrEntrada;
	private String hrFim;
	private String especialidade;
	private Endereco endereco;
	
	private String[] especialidades;
	
	
	
	public String[] getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(String[] especialidades) {
		this.especialidades = especialidades;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public double getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
//	public int getDia() {
//		return dia;
//	}
	public String getHrEntrada() {
		return hrEntrada;
	}
	public String getHrFim() {
		return hrFim;
	}
//	public void setDia(int dia) {
//		this.dia = dia;
//	}
	public void setHrEntrada(String hrEntrada) {
		this.hrEntrada = hrEntrada;
	}
	public void setHrFim(String hrFim) {
		this.hrFim = hrFim;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCodigoComanda() {
		return codigoComanda;
	}
	public void setCodigoComanda(String codigoComanda) {
		this.codigoComanda = codigoComanda;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public int getQtdMesas() {
		return qtdMesas;
	}
	public void setQtdMesas(int qtdMesas) {
		this.qtdMesas = qtdMesas;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Restaurante [cnpj=" + cnpj + ", qtdMesas=" + qtdMesas + ", codigoComanda=" + codigoComanda
				+ ", razaoSocial=" + razaoSocial + ", nomeFantasia=" + nomeFantasia + ", status=" + status + ", logo="
				+ logo + ", descricao=" + descricao + ", hrEntrada=" + hrEntrada + ", hrFim=" + hrFim
				+ ", especialidade=" + especialidade + ", endereco=" + endereco + "]";
	}
	
	
}
