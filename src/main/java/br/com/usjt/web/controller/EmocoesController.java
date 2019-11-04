package br.com.usjt.web.controller;

import java.util.ArrayList;
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
public class EmocoesController {

	@Inject
	Result result;

	
	//treina o algoritimo carregando os dados do banco
	@Path("/trainBayes")
	public void trainBayes() {
		result.use(Results.status()).header("Access-Control-Allow-Origin", "*");
		try {

			NaiveBayes bayes = NaiveBayes.getInstance();

			AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

			//TODO AvalicaoDAO.getAvaliacoes() // todas as avaliacoes do banco
			List<Avaliacao> avaliacoes = avaliacaoDAO.getAvaliacoesTodas();
			List<String> frases = new ArrayList<>();
			List<Integer> estrelas = new ArrayList<>();
			
			
			for (Avaliacao a : avaliacoes) {
				frases.add(a.getDescEstabelecimento());
				estrelas.add(Integer.parseInt(a.getAvEstabelecimento()));
				frases.add(a.getDescFuncionario());		
				estrelas.add(Integer.parseInt(a.getAvFuncionario()));
			}

			String[] fTrain = new String[frases.size()];
			Integer[] eTrain = new Integer[estrelas.size()];
			fTrain = frases.toArray(fTrain);
			eTrain = estrelas.toArray(eTrain);

			
			bayes.train(fTrain, eTrain);

			result.use(Results.json()).withoutRoot().from("NaiveBayes Inicializado").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e).serialize();
			e.printStackTrace();
		}

	}
	
	//retorna uma string mal formatada com os tokens e frequencias
	@Path("/getBayesTable")
	public void getBayesTable() {
		
		result.use(Results.status()).header("Access-Control-Allow-Origin", "*");
	
		NaiveBayes bayes = NaiveBayes.getInstance();
		if (bayes.isEmpty()) trainBayes();
		result.use(Results.json()).withoutRoot().from(bayes.showTokenTable()).serialize();
		
	}
	
}
