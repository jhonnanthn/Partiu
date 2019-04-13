package br.com.usjt.web.controller;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.ComandaDAO;
import br.com.usjt.web.model.Comanda;

@Resource
public class ComandaController {
	@Inject
	Result result;
	
	@Path("/createComanda")
	public void createComanda(Comanda comanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try{
			comandaDAO.createComanda(comanda);		
			result.use(Results.json()).withoutRoot().from("Comanda criado com sucesso").serialize();
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from(e.getMessage()).serialize();
		}
	}
}