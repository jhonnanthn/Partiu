package br.com.usjt.web.controller;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.RestauranteDAO;
import br.com.usjt.web.model.Item;
import br.com.usjt.web.model.Restaurante;

@Resource
public class RestaurantController {
	@Inject
	Result result;
	
	@Path("/createRestaurante")
	public void createRestaurante(String cnpj, String codigoComanda, String razaoSocial, String nomeFantasia, 
			String qntMesas, String logotipo, String descricao, String status, String logradouro, String numero,
			String complemento, String bairro, String cidade, String uf, String cep) {
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		try{
			restauranteDAO.createRestaurante(cnpj, codigoComanda, razaoSocial, nomeFantasia, qntMesas, logotipo, descricao, status,
					logradouro, numero, complemento, bairro, cidade, uf, cep);		
			result.use(Results.json()).withoutRoot().from("Restaurante criado com sucesso").serialize();
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from(e.getMessage()).serialize();
		}
	}

	@Path("/updateRestaurante")
	public void updateRestaurante(String cnpj, String codigoComanda, String razaoSocial, String nomeFantasia, 
			String qntMesas, String logotipo, String descricao, String status) {
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		try{
			restauranteDAO.updateRestaurante(cnpj, codigoComanda, razaoSocial, nomeFantasia, qntMesas, logotipo, descricao, status);		
			result.use(Results.json()).withoutRoot().from("Dados alterados com sucesso").serialize();
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from(e.getMessage()).serialize();
		}
	}
	
	@Path("/updateEndereco")
	public void updateEndereco(String id, String logradouro, String numero, String complemento, String bairro, String cidade, String uf, String cep) {
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		try{
			restauranteDAO.updateEndereco(id, logradouro, numero, complemento, bairro, cidade, uf, cep);		
			result.use(Results.json()).withoutRoot().from("Dados alterados com sucesso").serialize();
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from(e.getMessage()).serialize();
		}
	}
	
	// get Restaurante pela idGarcom; Cada garcom só faz parte de 1 restaurante.
	// retorna Restaurante
	@Path("/getRestauranteByIdGarcom")
	public void getRestauranteByIdGarcom(int idGarcom) {
		result.use(Results.status()).header("Access-Control-Allow-Origin", "*");
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
	
	//get resturante de acordo com o cnpj
	@Path("/getRestauranteByCnpj")
	public void getRestauranteByCnpj(long cnpj) {
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		try{
			Restaurante restaurante = restauranteDAO.getRestauranteByCnpj(cnpj);
			result.use(Results.json()).withoutRoot().from(restaurante).include("endereco").serialize();
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from(e.getMessage()).serialize();
		}
	}
	
	//get resturante de acordo com o cnpj
	@Path("/getHorarioRestauranteByCnpj")
	public void getHorarioRestauranteByCnpj(long cnpj) {
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		try{
			List<Restaurante> restaurante = restauranteDAO.getHorarioRestauranteByCnpj(cnpj);
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
