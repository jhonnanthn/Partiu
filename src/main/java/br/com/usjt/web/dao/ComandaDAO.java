package br.com.usjt.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.usjt.web.model.Comanda;
import br.com.usjt.web.model.Item;
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
			System.out.println(itens.get(1).getCnpjRestaurante());
			
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

	public void createItemPedidoUsuario(List<Item> itens, int idUsuario, int idComanda) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			comandaMapper.createItemPedidoUsuario(itens, idUsuario, idComanda);
			session.commit();
		} finally {
			session.close();
		}		
	}
}
