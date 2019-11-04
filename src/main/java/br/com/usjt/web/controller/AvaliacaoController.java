package br.com.usjt.web.controller;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.AvaliacaoDAO;
import br.com.usjt.web.model.Avaliacao;
import br.com.usjt.web.model.NaiveBayes;

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
			avaliacaoDAO.createAvaliacao(idCliente, idComanda, avEstabelecimento, avFuncionario, descEstabelecimento,
					descFuncionario);

			NaiveBayes bayes = NaiveBayes.getInstance();
			String[] fTrain = new String[2];
			Integer[] eTrain = new Integer[2];
			fTrain[0] = descEstabelecimento;
			fTrain[1] = descFuncionario;
			eTrain[0] = avEstabelecimento;
			eTrain[1] = avFuncionario;
			bayes.train(fTrain, eTrain);

			result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Avalicão criada!").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e).serialize();
		}
	}

	@Path("/getAvaliacaoByCnpj")
	public void getAvaliacaoByCnpj(long cnpj) {
		result.use(Results.status()).header("Access-Control-Allow-Origin", "*");
		AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
		try {
			List<Avaliacao> avaliacao = avaliacaoDAO.getAvaliacaoByCnpj(cnpj);

			NaiveBayes bayes = NaiveBayes.getInstance();

			for (Avaliacao a : avaliacao) {
				a.setBayesEstabelecimento(bayes.getRating(a.getDescEstabelecimento()));
				a.setBayesFuncionario(bayes.getRating(a.getDescFuncionario()));
			}
			result.use(Results.json()).withoutRoot().from(avaliacao).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e).serialize();
			e.printStackTrace();
		}
	}
}