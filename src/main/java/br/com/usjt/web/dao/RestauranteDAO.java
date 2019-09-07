package br.com.usjt.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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
	
	public void createRestaurante(String cnpj, String codigoComanda, String razaoSocial, String nomeFantasia, 
			String qntMesas, String logotipo, String descricao, String status, String logradouro, String numero,
			String complemento, String bairro, String cidade, String uf, String cep) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			EnderecoMapper enderecoMapper = session.getMapper(EnderecoMapper.class);
			//cria endereco antes
			enderecoMapper.createEndereco(logradouro, numero, complemento, bairro, cidade, uf, cep);
			//cria restaurante. id do restaurante é o ultimo criado
			restauranteMapper.createRestaurante(cnpj, codigoComanda, razaoSocial, nomeFantasia, qntMesas, logotipo, descricao, status);
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

	public List<Restaurante> getHorarioRestauranteByCnpj(long cnpj) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			List<Restaurante> restaurante = restauranteMapper.getHorarioRestauranteByCnpj(cnpj);
			return restaurante;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}

	public void updateRestaurante(String cnpj, String codigoComanda, String razaoSocial, String nomeFantasia,
			String qntMesas, String logotipo, String descricao, String status) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			restauranteMapper.updateRestaurante(cnpj, codigoComanda, razaoSocial, nomeFantasia,
					qntMesas, logotipo, descricao, status);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void updateEndereco(String id, String logradouro, String numero, String complemento, String bairro, String cidade,
			String uf, String cep) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EnderecoMapper enderecoMapper = session.getMapper(EnderecoMapper.class);
			//cria endereco antes
			enderecoMapper.updateEndereco(id, logradouro, numero, complemento, bairro, cidade, uf, cep);
			session.commit();
		} finally {
			session.close();
		}
		
	}
	
}
