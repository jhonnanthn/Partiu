package br.com.usjt.web.controller;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.RestauranteDAO;
import br.com.usjt.web.model.Restaurante;

@Resource
public class RestaurantController {
	@Inject
	Result result;
	
	@Path("/createRestaurante")
	public void createRestaurante(Restaurante restaurante) {
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		try{
			restauranteDAO.createRestaurante(restaurante);		
			result.use(Results.json()).withoutRoot().from("Comanda criado com sucesso").serialize();
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from(e.getMessage()).serialize();
		}
	}
}
