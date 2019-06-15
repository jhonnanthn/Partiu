package br.com.usjt.web.controller;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.RecomendacaoDAO;
import br.com.usjt.web.model.Item;
import br.com.usjt.web.model.Restaurante;

@Resource
@Path("/recomendacao")
public class RecomendacaoController {
	@Inject
	Result result;
	
	// Controller para geração de recomendações. Sempre gera uma List<Restaurante>
	// Todos os paths comecam com /recomendacao
	//TODO inserir novamente imagens NOS XMLs
	@Path("/getRecomendacaoDiaSemana")
	public void getRecomendacaoDiaSemana() {
		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
		try {
			List<Restaurante> restaurantes = recomendacaoDAO.getRecomendacaoDiaSemana();
			result.use(Results.json()).withoutRoot().from(restaurantes).include("endereco").serialize();
		}catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	
	@Path("/getRecomendacaoMaisVisitados")
	public void getRecomendacaoMaisVisitados() {
		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
		try {
			List<Restaurante> restaurantes = recomendacaoDAO.getRecomendacaoMaisVisitados();
			result.use(Results.json()).withoutRoot().from(restaurantes).include("endereco").serialize();
		}catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	
	@Path("/getRecomendacaoVisitadoRecentemente")
	public void getRecomendacaoVisitadoRecentemente(int idUsuario) {
		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
		try {
			List<Restaurante> restaurantes = recomendacaoDAO.getRecomendacaoVisitadoRecentemente(idUsuario);
			result.use(Results.json()).withoutRoot().from(restaurantes).include("endereco").serialize();
		}catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	
	@Path("/getRecomendacaoEspecialidadeUsuario")
	public void getRecomendacaoEspecialidadeUsuario(int idUsuario) {
		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
		try {
			List<Restaurante> restaurantes = recomendacaoDAO.getRecomendacaoEspecialidadeUsuario(idUsuario);
			result.use(Results.json()).withoutRoot().from(restaurantes).include("endereco").serialize();
		}catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	
	@Path("/getScoreByEspecialidadeUsuario")
	public void getScoreByEspecialidadeUsuario(int idUsuario) {
		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
		try {
			List<Item> itens = recomendacaoDAO.getScoreByEspecialidadeUsuario(idUsuario);
			result.use(Results.json()).withoutRoot().from(itens).serialize();
		}catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	
	@Path("/getRecomendacaoRestauranteAvaliado")
	public void getRecomendacaoRestauranteAvaliado() {
		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
		try {
			List<Restaurante> restaurantes = recomendacaoDAO.getRecomendacaoRestauranteAvaliado();
			result.use(Results.json()).withoutRoot().from(restaurantes).include("endereco").serialize();
		}catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
}
