package br.com.usjt.partiu.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import br.com.usjt.partiu.web.model.Usuario;


public interface UsuarioMapper {
	public List<String> findMatriculaDemitidosAssaiUltimos10Dias();
	
	public Usuario findUsuarioById(
		@Param("id_usuario") String id_usuario);
	
	Usuario getUsuarioByLogin(
			@Param("login") String login);
	
}