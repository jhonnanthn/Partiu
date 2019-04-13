package br.com.usjt.partiu.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	Integer idPerfil;
	private int idUsuario;
	private String matricula;
	private String centroCusto;
	private String dataCadastro;
	private String desenvolvedor;
	private String login;
	private String senha;
	private String qtdAcessos;
	private int idFilial;
	private String nome;
	private String email;
	private int indicadorStatus;
	private int indicadorSAU;
	private int indicadorEmail;
	private int indicadorInternet;
	private int indicadorMicroStrategy;
	private int indicadorGroupManager;	
	private Long idDocumento;
	
	public String getDesenvolvedor() {
		return desenvolvedor;
	}
	public void setDesenvolvedor(String desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}
	public String getQtdAcessos() {
		return qtdAcessos;
	}
	public void setQtdAcessos(String qtdAcessos) {
		this.qtdAcessos = qtdAcessos;
	}
	public Long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getIdFilial() {
		return idFilial;
	}
	public void setIdFilial(int idFilial) {
		this.idFilial = idFilial;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIndicadorStatus() {
		return indicadorStatus;
	}
	public void setIndicadorStatus(int indicadorStatus) {
		this.indicadorStatus = indicadorStatus;
	}
	public int getIndicadorSAU() {
		return indicadorSAU;
	}
	public void setIndicadorSAU(int indicadorSAU) {
		this.indicadorSAU = indicadorSAU;
	}
	public int getIndicadorEmail() {
		return indicadorEmail;
	}
	public void setIndicadorEmail(int indicadorEmail) {
		this.indicadorEmail = indicadorEmail;
	}
	public int getIndicadorInternet() {
		return indicadorInternet;
	}
	public void setIndicadorInternet(int indicadorInternet) {
		this.indicadorInternet = indicadorInternet;
	}
	public int getIndicadorMicroStrategy() {
		return indicadorMicroStrategy;
	}
	public void setIndicadorMicroStrategy(int indicadorMicroStrategy) {
		this.indicadorMicroStrategy = indicadorMicroStrategy;
	}
	public int getIndicadorGroupManager() {
		return indicadorGroupManager;
	}
	public void setIndicadorGroupManager(int indicadorGroupManager) {
		this.indicadorGroupManager = indicadorGroupManager;
	}
	public String getCentroCusto() {
		return centroCusto;
	}
	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}
}
