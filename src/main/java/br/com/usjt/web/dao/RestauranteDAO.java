package br.com.usjt.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.usjt.web.model.Restaurante;
import br.com.usjt.web.service.EnderecoMapper;
import br.com.usjt.web.service.RestauranteMapper;
public class RestauranteDAO {
	private SqlSessionFactory sqlSessionFactory;

	public RestauranteDAO() {
		sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
	}
	
	public void createRestaurante(Restaurante restaurante) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper comandaMapper = session.getMapper(RestauranteMapper.class);
			EnderecoMapper enderecoMapper = session.getMapper(EnderecoMapper.class);
			enderecoMapper.createEndereco(restaurante.getEndereco());
			comandaMapper.createRestaurante(restaurante);
			session.commit();
		} finally {
			session.close();
		}
	}
}
