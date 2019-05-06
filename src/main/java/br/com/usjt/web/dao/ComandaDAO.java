package br.com.usjt.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.usjt.web.model.Comanda;
import br.com.usjt.web.model.Item;
import br.com.usjt.web.service.ComandaMapper;
import javassist.bytecode.stackmap.BasicBlock.Catch;

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
			return null;
		} finally {
			session.close();
		}
	}
	
	public List<Item> getItensRestaurante(long cnpj) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ComandaMapper comandaMapper = session.getMapper(ComandaMapper.class);
			List<Item> itens = comandaMapper.getItensRestaurante(cnpj);
			return itens;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}

	public void createItemPedido(int[] itens, int idComanda) {
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
}
