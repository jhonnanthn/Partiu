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
	
	// createComanda chamado pelo garçom
	// retorna a comanda criada
	@Path("/createComanda")
	public void createComanda(int idGarcom, int mesa) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		ComandaDAO comandaDAO = new ComandaDAO();
		try{
		
			Usuario garcom = usuarioDAO.getUsuarioByParameter("" + idGarcom, "id").get(0);
			Restaurante restaurante = restauranteDAO.getRestauranteByIdGarcom(garcom.getId());
			//codigo em String baseado no codigo AAA e mesa 00
			String codigo = restaurante.getCodigoComanda()+String.format("%02d" , mesa);
			List<Comanda> comandaCheck = comandaDAO.checkComanda(codigo);
			//faz verificacao se existe comanda com o número da mesa em aberto
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
				// Retorna msg informando ao garcom que não é possivel criar comanda com esta mesa
				result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Mesa possui uma comanda não finalizada!").serialize();
			}
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	
	// verifica se existe comanda em aberta com codigo AAA00 igual; utilizado antes de criar uma nova comanda
	// retorna List<> de Comanda (sempre tem size 0 ou 1, pois ou há 1 comanda em aberto ou nenhuma)
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
	
	
	// getComandas por status e id; Utilizado principalmente pelo garçom para pegar as comandas em aberto
	// Retorna List<> de comandas
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
	
	// getComanda por id
	// Retorna List<> de comandas
	//TODO Carregar Usuario (garcom) junto à comanda
		@Path("/getComandaById")
		public void getComandaById(int idComanda) {
			ComandaDAO comandaDAO = new ComandaDAO();
			try {
				Comanda comanda = comandaDAO.getComandaById(idComanda);
				result.use(Results.json()).withoutRoot().from(comanda).serialize();
			}catch(Exception e) {
				result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
			}
		}
	
	//get Comanda pelo codigo AAA00; utilizado pelo cliente (inserir codigo comanda)
	// retorna Comanda
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
	
	// get Itens pedidos pelos clientes; Utilizado pelo Cliente para visualizar os itens atuais da comanda
	// retorna List<> de Item
	@Path("/getPedidosComanda")
	public void getPedidosComanda(int idComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			List<Item> itens = comandaDAO.getPedidosComanda(idComanda);
			result.use(Results.json()).withoutRoot().from(itens).serialize();
		}catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}

	// cria um Pedido referente aos itens selecionados pelo garcom, na adição de itens à comanda
	// retorna a nova lista de pedidos
	@Path("/createItemPedido")
	public void createItemPedido(int[] itens, int idComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try{
			comandaDAO.createItemPedido(itens, idComanda);
			comandaDAO.updateComandaDtaAtualizacao(idComanda);
			comandaDAO.getPedidosComanda(idComanda);
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	
	// irá vincular o codigo do usuário ao código da comanda que estiver ativa
	// retorna se foi possível o vínculo
	@Path("/vincularUsuarioComanda")
	public void vincularUsuarioComanda(int idUsuario, String codComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try{
			Comanda comanda = comandaDAO.getComandaByCodigo(codComanda);
			if(comanda.getStatus() == 'A') {
				comandaDAO.vincularUsuarioComanda(idUsuario, comanda.getId());
				result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Vinculado com sucesso.").serialize();
			} else {
				result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Comanda está finalizada.").serialize();
			}
		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	
	@Path("/testeObjetos")
	public void vincularUsuarioComanda() {
		ComandaDAO comandaDAO = new ComandaDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try{
			Comanda comanda = comandaDAO.getComandaByCodigo("OCN01");
			Usuario usuario = usuarioDAO.getUsuarioByParameter("id", "2").get(1);		
			result.use(Results.json()).withoutRoot().from(comanda).serialize();

		} catch(Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
}