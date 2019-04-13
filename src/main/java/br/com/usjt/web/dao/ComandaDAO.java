package br.com.usjt.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.usjt.web.model.Comanda;
import br.com.usjt.web.service.ComandaMapper;

public class ComandaDAO {
	private SqlSessionFactory sqlSessionFactory;

	public ComandaDAO() {
		sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
	}
	
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
}
