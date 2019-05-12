package br.com.usjt.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import br.com.usjt.web.model.Restaurante;

public interface RecomendacaoMapper {

	List<Restaurante> getRecomendacaoDiaSemana();

	List<Restaurante> getRecomendacaoMaisVisitados();
	
	List<Restaurante> getRecomendacaoRestauranteAvaliado();

	List<Restaurante> getRecomendacaoVisitadoRecentemente(
			@Param("idUsuario") int idUsuario);

	List<Restaurante> getRecomendacaoEspecidadeUsuario(
			@Param("idUsuario") int idUsuario);

}
