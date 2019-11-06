package br.com.usjt.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import br.com.usjt.web.model.Avaliacao;

public interface AvaliacaoMapper {
	
	//cria avaliacao
	void createAvaliacao(
			@Param("idCliente") int idCliente, 
			@Param("idComanda") int idComanda, 
			@Param("avEstabelecimento") int avEstabelecimento, 
			@Param("avFuncionario") int avFuncionario,
			@Param("descEstabelecimento") String descEstabelecimento, 
			@Param("descFuncionario") String descFuncionario);

	List<Avaliacao> getAvaliacaoByCnpj(
			@Param("cnpj") String cnpj);
	
	List<Avaliacao> getAvaliacoesTodas();
}
