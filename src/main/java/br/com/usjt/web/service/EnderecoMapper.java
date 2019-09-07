package br.com.usjt.web.service;

import org.apache.ibatis.annotations.Param;

public interface EnderecoMapper {
	//cria endereco
	void createEndereco(
			@Param("logradouro") String logradouro,
			@Param("numero") String numero,
			@Param("complemento") String complemento,
			@Param("bairro") String bairro,
			@Param("cidade") String cidade,
			@Param("uf") String uf,
			@Param("cep") String cep);

	void updateEndereco(
			@Param("id") String id,
			@Param("logradouro") String logradouro,
			@Param("numero") String numero,
			@Param("complemento") String complemento,
			@Param("bairro") String bairro,
			@Param("cidade") String cidade,
			@Param("uf") String uf,
			@Param("cep") String cep);
}
