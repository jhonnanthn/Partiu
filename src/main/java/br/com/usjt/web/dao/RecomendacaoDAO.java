package br.com.usjt.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.usjt.web.model.Restaurante;
import br.com.usjt.web.service.EnderecoMapper;
import br.com.usjt.web.service.RestauranteMapper;
public class RecomendacaoDAO {
	private SqlSessionFactory sqlSessionFactory;

	public RecomendacaoDAO() {
		sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
	}
	
	public List<Restaurante> getRecomendacaoDiaSemana() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			//TODO mapper getRecomendacaoDiaSemana()
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			List<Restaurante> restaurante = restauranteMapper.getRestaurantes();
			return restaurante;
		} finally {
			session.close();
		}
	}
	
}
