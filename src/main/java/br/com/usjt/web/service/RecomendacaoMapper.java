package br.com.usjt.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import br.com.usjt.web.model.Item;
import br.com.usjt.web.model.Restaurante;

public interface RecomendacaoMapper {

	List<Restaurante> getRecomendacaoDiaSemana();

	List<Restaurante> getRecomendacaoMaisVisitados();
	
	List<Restaurante> getRecomendacaoRestauranteAvaliado();

	List<Restaurante> getRecomendacaoVisitadoRecentemente(
			@Param("idUsuario") int idUsuario);

	List<Restaurante> getRecomendacaoEspecialidadeUsuario(
			@Param("idUsuario") int idUsuario);

	List<Item> getScoreByEspecialidadeUsuario(
			@Param("idUsuario") int idUsuario);
	
	List<Restaurante> getRestaurantesByCnpjs(
			@Param("cnpjs") List<String> cnpjs);
	

}
