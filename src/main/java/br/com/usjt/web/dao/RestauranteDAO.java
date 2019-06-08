package br.com.usjt.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.usjt.web.model.Item;
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
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			EnderecoMapper enderecoMapper = session.getMapper(EnderecoMapper.class);
			//cria endereco antes
			enderecoMapper.createEndereco(restaurante.getEndereco());
			//cria restaurante. id do restaurante é o ultimo criado
			restauranteMapper.createRestaurante(restaurante);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	public Restaurante getRestauranteByIdGarcom(int idGarcom) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			Restaurante restaurante = restauranteMapper.getRestauranteByIdGarcom(idGarcom);
			return restaurante;
		} finally {
			session.close();
		}
	}

	public List<Restaurante> getRestaurantes() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			List<Restaurante> restaurante = restauranteMapper.getRestaurantes();
			return restaurante;
		} finally {
			session.close();
		}
	}
	
	public List<Item> getItensRestaurante(long cnpj) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			List<Item> itens = restauranteMapper.getItensRestaurante(cnpj);
			return itens;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}

	public Restaurante getRestauranteByCnpj(long cnpj) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			Restaurante restaurante = restauranteMapper.getRestauranteByCnpj(cnpj);
			return restaurante;
		} finally {
			session.close();
		}
	}
	
}
