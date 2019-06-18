package br.com.usjt.web.controller;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.AvaliacaoDAO;
import br.com.usjt.web.model.Avaliacao;

@Resource
public class AvaliacaoController {
	@Inject
	Result result;

	// createAvaliacao informada pelo usuário
	// retorna a avaliacao criada
	@Path("/createAvaliacao")
	public void createAvaliacao(int idCliente, int idComanda, int avEstabelecimento, int avFuncionario, 
			String descEstabelecimento, String descFuncionario) {
		AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
		try {
			avaliacaoDAO.createAvaliacao(idCliente, idComanda, avEstabelecimento, avFuncionario, descEstabelecimento, descFuncionario);
			result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Avalicão criada!").serialize();
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e).serialize();
		}
	}

	@Path("/getAvaliacaoByCnpj")
	public void getAvaliacaoByCnpj(long cnpj) {
		AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
		try {
			List<Avaliacao> avaliacao = avaliacaoDAO.getAvaliacaoByCnpj(cnpj);
			result.use(Results.json()).withoutRoot().from(avaliacao).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e).serialize();
			e.printStackTrace();
		}
	}
}