package br.com.usjt.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.google.inject.Inject;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.RecomendacaoDAO;
import br.com.usjt.web.model.PerfisSingleton;
import br.com.usjt.web.model.Restaurante;

@Resource
@Path("/recomendacao")
public class RecomendacaoController {
	@Inject
	Result result;

	// Controller para geração de recomendações. Sempre gera uma List<Restaurante>
	// Todos os paths comecam com /recomendacao
	// TODO inserir novamente imagens NOS XMLs
	@Path("/getRecomendacaoDiaSemana")
	public void getRecomendacaoDiaSemana() {
		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
		try {
			List<Restaurante> restaurantes = recomendacaoDAO.getRecomendacaoDiaSemana();
			result.use(Results.json()).withoutRoot().from(restaurantes).include("endereco").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	@Path("/getRecomendacaoMaisVisitados")
	public void getRecomendacaoMaisVisitados() {
		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
		try {
			List<Restaurante> restaurantes = recomendacaoDAO.getRecomendacaoMaisVisitados();
			result.use(Results.json()).withoutRoot().from(restaurantes).include("endereco").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	@Path("/getRecomendacaoVisitadoRecentemente")
	public void getRecomendacaoVisitadoRecentemente(int idUsuario) {
		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
		try {
			List<Restaurante> restaurantes = recomendacaoDAO.getRecomendacaoVisitadoRecentemente(idUsuario);
			result.use(Results.json()).withoutRoot().from(restaurantes).include("endereco").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	@Path("/getRecomendacaoEspecialidadeUsuario")
	public void getRecomendacaoEspecialidadeUsuario(int idUsuario) {
		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
		try {
			List<Restaurante> restaurantes = recomendacaoDAO.getRecomendacaoEspecialidadeUsuario(idUsuario);
			result.use(Results.json()).withoutRoot().from(restaurantes).include("endereco").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	@Path("/restaurantesByScoreContent")
	public void restaurantesByScoreContent(int idUsuario) {
		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
		PerfisSingleton perfis = PerfisSingleton.getInstance();
		try {
			List<Restaurante> restaurantes = recomendacaoDAO.restaurantesByScoreContent(idUsuario);
			perfis.printPerfil(perfis.getPerfil(idUsuario));
			result.use(Results.json()).withoutRoot().from(restaurantes).include("endereco").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

//	@Path("/restaurantesByScoreCollab")
//	public void restaurantesByScoreCollab(int idUsuario) {
//		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
//		try {
//			int idNeighbor = recomendacaoDAO.collabFiltering(idUsuario);
//			List<Restaurante> restaurantes = recomendacaoDAO.getRecomendacaoEspecialidadeUsuario(idNeighbor);
//			result.use(Results.json()).withoutRoot().from(restaurantes).include("endereco").serialize();
//		}catch(Exception e) {
//			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
//		}
//	}
//	

	@Path("/getRecomendacaoRestauranteAvaliado")
	public void getRecomendacaoRestauranteAvaliado() {
		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
		try {
			List<Restaurante> restaurantes = recomendacaoDAO.getRecomendacaoRestauranteAvaliado();

			result.use(Results.json()).withoutRoot().from(restaurantes).include("endereco").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}
}
