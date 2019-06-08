package br.com.usjt.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.usjt.web.model.Comanda;
import br.com.usjt.web.model.Item;
import br.com.usjt.web.model.Usuario;
import br.com.usjt.web.service.ComandaMapper;

public class ComandaDAO {
	private SqlSessionFactory sqlSessionFactory;

	public ComandaDAO() {
		sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
	}
	
	//cria Comanda
	public void createComanda(Comanda comanda) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			comandaMapper.createComanda(comanda);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	public Integer[] getIdsUsuarioComanda(int idComanda) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			session.commit();
			return comandaMapper.getIdsUsuarioComanda(idComanda);
			
		} finally {
			session.close();
		}
	}
	
	
	// Lista Comandas por status e Id
	public List<Comanda> getComandasByStatus(int idGarcom, char status) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			List<Comanda> comandas = comandaMapper.getComandasByStatusAndId(idGarcom, status);
			return comandas;
		} finally {
			session.close();
		}
	}
	
	public Comanda getComandaById(int idComanda) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			Comanda comandas = comandaMapper.getComandaById(idComanda);
			return comandas;
		} finally {
			session.close();
		}
	}
	
	public Comanda getComandaByCodigo(String codigo) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			Comanda comanda = comandaMapper.getComandaByCodigo(codigo);
			return comanda;
		} finally {
			session.close();
		}
	}

	public List<Comanda> checkComanda(String codigo) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			List<Comanda> comandas = comandaMapper.checkComanda(codigo);
			return comandas;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}
		
	public List<Item> getPedidosComanda(int idComanda) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			List<Item> itens = comandaMapper.getPedidosComanda(idComanda);
			return itens;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	public void createItemPedido(List<Item> itens, int idComanda) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			comandaMapper.createItemPedido(itens, idComanda);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void updateComanda(Comanda comanda) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			comandaMapper.updateComanda(comanda);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void updateComandaDtaAtualizacao(int idComanda) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			comandaMapper.updateComandaDtaAtualizacao(idComanda);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void vincularUsuarioComanda(int idUsuario, int idComanda) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			comandaMapper.vincularUsuarioComanda(idUsuario, idComanda);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void createItemPedidoUsuario(List<Item> itens, double porcentagemPagar, int idUsuario, int idComanda) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			comandaMapper.createItemPedidoUsuario(itens, porcentagemPagar, idUsuario, idComanda);
			session.commit();
		} finally {
			session.close();
		}		
	}
	
	public List<Usuario> getUsuarioByComanda(int idComanda) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			List<Usuario> usuario = comandaMapper.getUsuarioByComanda(idComanda);
			
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public void finalizarItemPedidoUsuario(int idComanda, int idUsuario) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			comandaMapper.finalizarComandaUsuario(idComanda, idUsuario);
			comandaMapper.finalizarItemPedidoUsuario(idComanda, idUsuario);
			session.commit();
		} finally {
			session.close();
		}	
	}

	public void finalizarComanda(int idComanda) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			comandaMapper.finalizarComanda(idComanda);
			session.commit();
		} finally {
			session.close();
		}	
	}

	public void removerPedidoComandaByUsuario(int idUsuario, int idPedido) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			comandaMapper.removerPedidoComandaByUsuario(idUsuario, idPedido);
			session.commit();
		} finally {
			session.close();
		}	
	}

	public void removerPedidoComanda(int idPedido) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			comandaMapper.removerPedidoComanda(idPedido);
			session.commit();
		} finally {
			session.close();
		}	
	}

	public void updateStatusPedidoById(int idPedido, String status) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			comandaMapper.updateStatusPedidoById(idPedido, status);
			session.commit();
		} finally {
			session.close();
		}
	}

	public List<Item> getPedidoUsuarioBydId(int idPedido) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			List<Item> pedido = comandaMapper.getPedidoUsuarioBydId(idPedido);
			return pedido;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	
	public Item getPedidoById(int idPedido) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			Item item = comandaMapper.getPedidoById(idPedido);
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public List<Item> getPedidosByUsuario(int idComanda, int idUsuario) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			List<Item> item = comandaMapper.getPedidosByUsuario(idComanda, idUsuario);
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public void updatePedidoUsuarioByIdPedido(int idComanda, int idPedido, int idUsuario, double porcPaga) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			comandaMapper.updatePedidoUsuarioByIdPedido(idComanda, idPedido, idUsuario, porcPaga);
			session.commit();
		} finally {
			session.close();
		}
	}

	public int entrarComanda(String codigo, int idUsuario) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			int comanda = comandaMapper.entrarComanda(codigo, idUsuario);
			return comanda;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return -1;
	}
}
