package br.com.usjt.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import br.com.usjt.web.model.Comanda;
import br.com.usjt.web.model.Item;
import br.com.usjt.web.model.Usuario;

public interface ComandaMapper {

	// Define parametros utilizados em data.Usuario.xml

	
	//pega todas comandas
	List<Comanda> getComandas();
	
	//pega todas comandas por status
	List<Comanda> getComandasByStatusAndId(
			@Param ("idGarcom") int garcom,
			@Param ("status") char status
			);
	
	//pega comanda por codigo
	Comanda getComandaByCodigo(
			@Param("codigo") String codigo);

	//cria comanda
	void createComanda(
			@Param("comanda") Comanda comanda);
	
	//verifica se existe comanda
	List<Comanda> checkComanda(
			@Param("codigo") String codigo);

	//Cria pedido 9Garcom
	void createItemPedido(
			@Param("itens") List<Item> itens,
			@Param("idComanda") int idComanda);

	//Atualiza Comanda
	void updateComanda(
			@Param("comanda") Comanda comanda);
	
	//lista pedidos (cliente)
	List<Item> getPedidosComanda(
			 @Param("idComanda") int idComanda);
	
	//Atuaiza dta_atualizacao da comanda
	void updateComandaDtaAtualizacao(
			@Param("idComanda") int idComanda);
	
	void vincularUsuarioComanda(
			@Param("idUsuario") int idUsuario, 
			@Param("idComanda") int idComanda);

	Comanda getComandaById(
			@Param("idComanda") int idComanda);

	void createItemPedidoUsuario(
			@Param("itens") List<Item> itens,
			@Param("idUsuario") int idUsuario,
			@Param("idComanda") int idComanda);

	List<Usuario> getUsuarioByComanda(
			@Param("idComanda") int idComanda);

	void finalizarComandaUsuario(
			@Param("idComanda") int idComanda, 
			@Param("idUsuario") int idUsuario);

	void finalizarComanda(
			@Param("idComanda") int idComanda);

	void removerPedidoComandaByUsuario(
			@Param("idPedido") int idPedido);

	void removerPedidoComanda(
			@Param("idPedido") int idPedido);

	void finalizarItemPedidoUsuario(
			@Param("idComanda") int idComanda, 
			@Param("idUsuario") int idUsuario);
}
