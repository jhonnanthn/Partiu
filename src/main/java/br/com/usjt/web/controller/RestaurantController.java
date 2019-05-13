package br.com.usjt.web.controller;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.ComandaDAO;
import br.com.usjt.web.dao.RestauranteDAO;
import br.com.usjt.web.model.Item;
import br.com.usjt.web.model.Restaurante;

@Resource
public class RestaurantController {
	@Inject
	Result result;
	
	// Não utilizado
	@Path("/createRestaurante")
	public void createRestaurante(Restaurante restaurante) {
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		try{
			restauranteDAO.createRestaurante(restaurante);		
			result.use(Results.json()).withoutRoot().from("Restaurante criado com sucesso").serialize();
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from(e.getMessage()).serialize();
		}
	}
	
	// get Restaurante pela idGarcom; Cada garcom só faz parte de 1 restaurante.
	// retorna Restaurante
	@Path("/getRestauranteByIdGarcom")
	public void getRestauranteByIdGarcom(int idGarcom) {
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		try{
			Restaurante restaurante = restauranteDAO.getRestauranteByIdGarcom(idGarcom);
			result.use(Results.json()).withoutRoot().from(restaurante).include("endereco").serialize();
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from(e.getMessage()).serialize();
		}
	}
	
	//get lista de restaurantes
	//Nao utilizado
	@Path("/getRestaurantes")
	public void getRestaurantes() {
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		try{
			List<Restaurante> restaurante = restauranteDAO.getRestaurantes();
			result.use(Results.json()).withoutRoot().from(restaurante).include("endereco").serialize();
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from(e.getMessage()).serialize();
		}
	}
	
	// get Itens de um Restaurante; Utilizado pelo garcom para ver itens disponíveis a serem adcionados
	// retorna List<> de Itens
	@Path("/getItensRestaurante")
	public void getItensRestaurante(long cnpj) {
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		try {
			List<Item> itens = restauranteDAO.getItensRestaurante(cnpj);
			result.use(Results.json()).withoutRoot().from(itens).serialize();
		}catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
}
