package br.com.usjt.web.controller;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.ComandaDAO;
import br.com.usjt.web.dao.RestauranteDAO;
import br.com.usjt.web.dao.UsuarioDAO;
import br.com.usjt.web.model.Comanda;
import br.com.usjt.web.model.Item;
import br.com.usjt.web.model.Restaurante;
import br.com.usjt.web.model.Usuario;

@Resource
public class ComandaController {
	@Inject
	Result result;
	
	@Path("/createComanda")
	public void createComanda(int idGarcom, int mesa) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		ComandaDAO comandaDAO = new ComandaDAO();
		try{
			
			Usuario garcom = usuarioDAO.getUsuarioByParameter("" + idGarcom, "id").get(0);
			Restaurante restaurante = restauranteDAO.getRestauranteByIdGarcom(garcom.getId());
			String codigo = restaurante.getCodigoComanda()+String.format("%02d" , mesa);
			List<Comanda> comandaCheck = comandaDAO.checkComanda(codigo);
			if (comandaCheck.size() == 0) {
				Comanda comanda = new Comanda();
				comanda.setGarcom(garcom);
				comanda.setMesa(mesa);
				comanda.setStatus('A');
				comanda.setCodigo(codigo);
				comandaDAO.createComanda(comanda);		
				comanda = comandaDAO.getComandaByCodigo(codigo);
				result.use(Results.json()).withoutRoot().from(comanda).serialize();				
			} else {
				result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Mesa possui uma comanda não finalizada!").serialize();
			}
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	
	@Path("/getComandasByStatusAndId")
	public void getComandasByStatusAndId(int idGarcom, char status) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			List<Comanda> comandas = comandaDAO.getComandasByStatus(idGarcom, status);
			result.use(Results.json()).withoutRoot().from(comandas).serialize();
		}catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	
	@Path("/getComandaByCodigo")
	public void getComandaByCodigo(String codigo) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			Comanda comanda = comandaDAO.getComandaByCodigo(codigo);
			result.use(Results.json()).withoutRoot().from(comanda).serialize();
		}catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	

	@Path("/checkComanda")
	public void checkComanda(String codigo) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try{
			List<Comanda> comandaCheck = comandaDAO.checkComanda(codigo);
				result.use(Results.json()).withoutRoot().from(comandaCheck).serialize();							
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	
	@Path("/createItemPedido")
	public void createItemPedido(List<Item> item) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try{
			comandaDAO.createItemPedido(item);
			Comanda comanda = comandaDAO.getComandaByCodigo(item.get(0).getIdComanda());
			comandaDAO.updateComanda(comanda);
			result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Pedido criado com sucesso").serialize();							
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
}