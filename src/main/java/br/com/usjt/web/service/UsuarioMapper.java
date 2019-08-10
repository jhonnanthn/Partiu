package br.com.usjt.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import br.com.usjt.web.model.Usuario;

public interface UsuarioMapper {
	// Define parametros utilizados em data.Usuario.xml
	
	List<Usuario> getIdsClientes();
	
	//	retorna usuario por id
	List<Usuario> getUsuarioByParameter(
			@Param("variavel") String variavel,
			@Param("valor") String valor);
	
	// retorna usuario com login
	Usuario getUsuarioOnLogin(
			@Param("email") String email,
			@Param("senha") String senha);

	//cria usuario
	void createUsuario(
			@Param("tipo") String tipo, 
			@Param("cpf") String cpf, 
			@Param("nome") String nome, 
			@Param("dta_nascimento") String dta_nascimento, 
			@Param("email") String email, 
			@Param("ddd") String ddd, 
			@Param("telefone") String telefone, 
			@Param("genero") String genero, 
			@Param("senha") String senha);
	
	//cria endereco
	void createEndereco(
			@Param("logradouro") String logradouro, 
			@Param("numero") String numero, 
			@Param("complemento") String complemento, 
			@Param("bairro") String bairro, 
			@Param("cidade") String cidade, 
			@Param("uf") String uf, 
			@Param("cep") String cep);
	
	//atualiza usuario (Segundo Semestre)
	void updateUsuario(
			@Param("usuario") Usuario usuario);
	
}
