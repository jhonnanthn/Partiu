package br.com.usjt.web.controller;

import java.util.ArrayList;
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
		try {

			Usuario garcom = usuarioDAO.getUsuarioByParameter("id", "" + idGarcom).get(0);
			Restaurante restaurante = restauranteDAO.getRestauranteByIdGarcom(garcom.getId());
			// codigo em String baseado no codigo AAA e mesa 00
			String codigo = restaurante.getCodigoComanda() + String.format("%02d", mesa);
			List<Comanda> comandaCheck = comandaDAO.checkComanda(codigo);
			// faz verificacao se existe comanda com o número da mesa em aberto
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
				// Retorna msg informando ao garcom que não é possivel criar comanda com esta
				// mesa
				result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Mesa possui uma comanda não finalizada!")
						.serialize();
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// verifica se existe comanda em aberta com codigo AAA00 igual; utilizado antes
	// de criar uma nova comanda
	// retorna List<> de Comanda (sempre tem size 0 ou 1, pois ou há 1 comanda em
	// aberto ou nenhuma)
	@Path("/checkComanda")
	public void checkComanda(String codigo) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			List<Comanda> comandaCheck = comandaDAO.checkComanda(codigo);
			result.use(Results.json()).withoutRoot().from(comandaCheck).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	@Path("/getIdsUsuarioComanda")
	public void getIdsUsuarioComanda(int idComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			Integer[] ids = comandaDAO.getIdsUsuarioComanda(idComanda);
			result.use(Results.json()).withoutRoot().from(ids).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e).serialize();
			e.printStackTrace();
		}
	}

	// getComandas por status e id; Utilizado principalmente pelo garçom para pegar
	// as comandas em aberto
	// Retorna List<> de comandas
	@Path("/getComandasByStatusAndId")
	public void getComandasByStatusAndId(int idGarcom, char status) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			List<Comanda> comandas = comandaDAO.getComandasByStatus(idGarcom, status);
			result.use(Results.json()).withoutRoot().from(comandas).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}
	
	// getComandas por status e id; Utilizado para listar as comandas
	// para o usuário
	// Retorna List<> de comandas
	@Path("/getComandasByCpf")
	public void getComandasByCpf(long cpf) {
		result.use(Results.status()).header("Access-Control-Allow-Origin", "*");
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			List<Comanda> comandas = comandaDAO.getComandasByCpf(cpf);
			result.use(Results.json()).withoutRoot().from(comandas).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// getComanda por id
	// Retorna List<> de comandas
	// TODO Carregar Usuario (garcom) junto à comanda
	@Path("/getComandaById")
	public void getComandaById(int idComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			Comanda comanda = comandaDAO.getComandaById(idComanda);
			result.use(Results.json()).withoutRoot().from(comanda).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// get Data de Atualização da Comanda
	@Path("/getAtualizacaoComanda")
	public void getAtualizacaoComanda(int idComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			Comanda comanda = comandaDAO.getComandaById(idComanda);
			result.use(Results.json()).withoutRoot().from(comanda.getDtaAtualizacao()).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// get Comanda pelo codigo AAA00; utilizado pelo cliente (inserir codigo
	// comanda)
	// retorna Comanda
	@Path("/getComandaByCodigo")
	public void getComandaByCodigo(String codigo) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			Comanda comanda = comandaDAO.getComandaByCodigo(codigo);
			result.use(Results.json()).withoutRoot().from(comanda).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// get Comanda pelo codigo AAA00; utilizado pelo cliente (inserir codigo
	// comanda)
	// retorna Comanda
	@Path("/entrarComanda")
	public void entrarComanda(String codigo, int idUsuario) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			int qntItemAPagar = comandaDAO.entrarComanda(codigo, idUsuario);
			if (qntItemAPagar == 0) {
				result.use(Results.json()).withoutRoot().from("NOTIFICAÇÃO: Comanda paga").serialize();
			} else {
				result.use(Results.json()).withoutRoot().from("NOTIFICAÇÃO: Usuário não pagou a comanda").serialize();
			}
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// get Itens pedidos pelos clientes; Utilizado pelo Cliente para visualizar os
	// itens atuais da comanda
	// retorna List<> de Item
	@Path("/getPedidosComanda")
	public void getPedidosComanda(int idComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			List<Item> itens = comandaDAO.getPedidosComanda(idComanda);
			result.use(Results.json()).withoutRoot().from(itens).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	@Path("/getPedidoUsuarioBydId")
	public void getPedidoUsuarioBydId(int idPedido) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			List<Item> pedido = comandaDAO.getPedidoUsuarioBydId(idPedido);
			result.use(Results.json()).withoutRoot().from(pedido).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// cria um Pedido referente aos itens selecionados pelo garcom, na adição de
	// itens à comanda
	// retorna a nova lista de pedidos
	@Path("/createItemPedido")
	public void createItemPedido(int[] idItens, String[] obsItens, int idComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		List<Item> itens = new ArrayList<>();
		try {
			for (int i = 0; i < idItens.length; i++) {
				Item item = new Item();
				item.setId(idItens[i]);
				item.setObservacao(obsItens[i]);
				itens.add(item);
			}
			comandaDAO.createItemPedido(itens, idComanda);
			comandaDAO.updateComandaDtaAtualizacao(idComanda);
			List<Item> pedidos = comandaDAO.getPedidosComanda(idComanda);
			result.use(Results.json()).withoutRoot().from(pedidos).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// cria um Pedido referente aos itens selecionados pelo garcom, na adição de
	// itens à comanda
	// retorna a nova lista de pedidos
	@Path("/selecionarPedidoUsuario")
	public synchronized void selecionarPedidoUsuario(int[] idPedido, int idUsuario, int idComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		List<Item> itens = new ArrayList<>();
		try {
			for (int i = 0; i < idPedido.length; i++) {
				Item item = new Item();
				item.setId(idPedido[i]);
				itens.add(item);

				comandaDAO.updateStatusPedidoById(idPedido[i], "S");
			}
			comandaDAO.createItemPedidoUsuario(itens, 100, idUsuario, idComanda);
			comandaDAO.updateComandaDtaAtualizacao(idComanda);
			recalcularValorAPagar(idComanda, idUsuario, idPedido);
			List<Item> pedidos = comandaDAO.getPedidosComanda(idComanda);
			result.use(Results.json()).withoutRoot().from(pedidos).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// remover um pedido da comanda do usuário
	// retorna a nova lista de pedidos
	@Path("/deselecionarPedidoUsuario")
	public synchronized void deselecionarPedidoUsuario(int idPedido, int idComanda, int idUsuario) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			comandaDAO.removerPedidoComandaByUsuario(idUsuario, idPedido);
			List<Item> pedidos = comandaDAO.getPedidosComanda(idComanda);
			comandaDAO.updateComandaDtaAtualizacao(idComanda);

			boolean pedidoSelecionado = false;

			for (Item pedido : pedidos) {
				if (pedido.getIdPedido() == idPedido && pedido.getIdUsuario() != 0 && pedido.getIdUsuario() != idUsuario
						&& !pedido.getStatusPedidoUsuario().equals("P")) {
					pedidoSelecionado = true;
				}
			}

			if (!pedidoSelecionado) {
				comandaDAO.updateStatusPedidoById(idPedido, "N");
			}

			int pedido[] = new int[1];
			pedido[0] = idPedido;

			recalcularValorAPagar(idComanda, 0, pedido);
			result.use(Results.json()).withoutRoot().from(pedidos).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// irá vincular o codigo do usuário ao código da comanda que estiver ativa
	// retorna se foi possível o vínculo
	@Path("/vincularUsuarioComanda")
	public void vincularUsuarioComanda(int idUsuario, int idComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			Integer[] ids = comandaDAO.getIdsUsuarioComanda(idComanda);
			boolean jaExiste = false;
			for (Integer id : ids) {
				if (id == idUsuario) {
					jaExiste = true;
					return;
				}
			}
			if (!jaExiste) {
				comandaDAO.vincularUsuarioComanda(idUsuario, idComanda);
				result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Vinculado com sucesso.").serialize();
			} else {
				result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Usuario Já Presente na Comanda.")
						.serialize();
			}

		} catch (

		Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// finaliza um Pedido referente aos itens selecionados pelo garcom, na adição de
	// itens à comanda
	// retorna a nova lista de pedidos
	@Path("/finalizarItemPedidoUsuario")
	public void finalizarItemPedidoUsuario(int idUsuario, int idComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
//		int verificaStatus = comandaDAO.verificaStatusPedido(idComanda);
//		if (verificaStatus == 0) {
		try {
			comandaDAO.finalizarItemPedidoUsuario(idComanda, idUsuario);
			List<Usuario> usuarios = comandaDAO.getUsuarioByComanda(idComanda);
			boolean finalizarComanda = true;
			for (Usuario usuario : usuarios) {
				if (Integer.parseInt(usuario.getStatus()) > 0) {
					finalizarComanda = false;
				}
			}

			if (finalizarComanda) {
				comandaDAO.finalizarComanda(idComanda);
			}
			result.use(Results.json()).withoutRoot().from("Comanda do usuário finalizada com sucesso.").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
//		} else {
//			result.use(Results.json()).withoutRoot().from("ERRO: Comanda possui pedido sem usuário.").serialize();
//		}
	}
 
	// Set usuario_pedido status como P, pedido status como P, fecha a comanda e atualiza dataAtualizacao
	@Path("/finalizarPedidosComanda")
	public void finalizarPedidosComanda(int idComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			comandaDAO.finalizarPedidosComanda(idComanda);
			result.use(Results.json()).withoutRoot().from("Comanda e seus pedidos finalizados com sucesso.").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	} 

	// remover um pedido da comanda
	// retorna a nova lista de pedidos
	@Path("/removerPedidoComanda")
	public void removerPedidoComanda(int idPedido, int idComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			comandaDAO.removerPedidoComanda(idPedido);
			List<Item> pedidos = comandaDAO.getPedidosComanda(idComanda);
			comandaDAO.updateComandaDtaAtualizacao(idComanda);
			result.use(Results.json()).withoutRoot().from(pedidos).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// Marcar pedido como check
	// retorna pedido marcado pelo usuário
	@Path("/updateStatusPedidoById")
	public void updateStatusPedidoById(int idComanda, int idPedido, String status) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			Item pedido = comandaDAO.getPedidoById(idPedido);
			if (pedido.getStatus().equals("S") && status.equals("S")) {
				result.use(Results.json()).withoutRoot().from("Pedido já selecionado.").serialize();
			} else if (pedido.getStatus().equals("P")) {
				result.use(Results.json()).withoutRoot().from("Pedido já está pago.").serialize();
			} else {
				comandaDAO.updateStatusPedidoById(idPedido, status);
				List<Item> itens = comandaDAO.getPedidosComanda(idComanda);
				result.use(Results.json()).withoutRoot().from(itens).serialize();
			}
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// Buscar resumo de pedidos do usuário
	// retorna os pedidos do usuário
	@Path("/getPedidosByUsuario")
	public void getPedidosByUsuario(int idComanda, int idUsuario) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			List<Item> itens = comandaDAO.getPedidosByUsuario(idComanda, idUsuario);
			result.use(Results.json()).withoutRoot().from(itens).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// Buscar resumo de pedidos do usuário
	// retorna os pedidos do usuário
	@Path("/getPedidosEmAbertoByComanda")
	public void getPedidosEmAbertoByComanda(int idComanda) {
		ComandaDAO comandaDAO = new ComandaDAO();
		try {
			List<Item> itens = comandaDAO.getPedidosEmAbertoByComanda(idComanda);
			result.use(Results.json()).withoutRoot().from(itens).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// Seleciona o pedido do usuário e faz o cálculo de quanto irá pagar
	// retorna os pedidos do usuário
	public void recalcularValorAPagar(int idComanda, int idUsuario, int idPedido[]) {
		ComandaDAO comandaDAO = new ComandaDAO();
		List<Item> itens = comandaDAO.getPedidosComanda(idComanda);
		comandaDAO.updateComandaDtaAtualizacao(idComanda);
		try {
			for (int i = 0; i < idPedido.length; i++) {
				List<Item> itemPedido = new ArrayList<>();
				int contPedidosPago = 0;
				double porcPaga = 0, porcAPagar = 0;
				for (Item item : itens) {
					if (item.getIdPedido() == idPedido[i] && item.getIdUsuario() != 0) {
						itemPedido.add(item);
						if (item.getStatusPedidoUsuario().equals("P")) {
							porcPaga += item.getPorcPaga();
							contPedidosPago += 1;
						}
					}
				}

				porcAPagar = (100 - porcPaga) / (itemPedido.size() - contPedidosPago);
				System.out.println(porcAPagar);
				comandaDAO.updatePedidoUsuarioByIdPedido(idComanda, idPedido[i], idUsuario, porcAPagar);
//				if(idUsuario != 0)	comandaDAO.updatePedidoUsuarioByIdPedido(idComanda, idPedido[i], idUsuario, porcAPagar);

			}
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

//	@Path("/testeObjetos")
//	public void vincularUsuarioComanda() {
//		ComandaDAO comandaDAO = new ComandaDAO();
//		UsuarioDAO usuarioDAO = new UsuarioDAO();
//		try{
//			Comanda comanda = comandaDAO.getComandaByCodigo("OCN01");
//			Usuario usuario = usuarioDAO.getUsuarioByParameter("id", "2").get(1);		
//			result.use(Results.json()).withoutRoot().from(comanda).serialize();
//
//		} catch(Exception e) {
//			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
//		}
//	}
}