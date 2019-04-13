package br.com.usjt.web.model;

public class Usuario {
	private String idUsuario;
	private String matricula;
	private String usuarioLogado;
	private String usuarioLogin;
	private String idFilialBase;
	
	public String getIdFilialBase() {
		return idFilialBase;
	}
	public void setIdFilialBase(String idFilialBase) {
		this.idFilialBase = idFilialBase;
	}
	public String getUsuarioLogin() {
		return usuarioLogin;
	}
	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
}
