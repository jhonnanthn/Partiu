package br.com.usjt.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import br.com.usjt.web.model.Usuario;

public interface UsuarioMapper {
	// Define parametros utilizados em data.Usuario.xml
	
	//	retorna usuario por id
	List<Usuario> getUsuarioByParameter(
			@Param("variavel") String variavel,
			@Param("valor") String valor);
	
	// retorna usuario com login
	Usuario getUsuarioOnLogin(
			@Param("email") String email,
			@Param("senha") String senha);

	//cria usuario (Segundo Semestre)
	void createUsuario(
			@Param("usuario") Usuario usuario);
	
	//atualiza usuario (Segundo Semestre)
	void updateUsuario(
			@Param("usuario") Usuario usuario);
}
