package br.com.usjt.web.controller;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.RecomendacaoDAO;
import br.com.usjt.web.model.Restaurante;

@Resource
public class RecomendacaoController {
	@Inject
	Result result;
	
	// Controller para geração de recomendações. Sempre gera uma List<Restaurante>
	// Todos os paths comecam com /recomendacao
	
	@Path("/recomendacao/getRecomendacaoDiaSemana")
	public void getRecomendacaoDiaSemana() {
		RecomendacaoDAO recomendacaoDAO = new RecomendacaoDAO();
		try {
			List<Restaurante> restaurantes = recomendacaoDAO.getRecomendacaoDiaSemana();
			result.use(Results.json()).withoutRoot().from(restaurantes).serialize();
		}catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	
}
