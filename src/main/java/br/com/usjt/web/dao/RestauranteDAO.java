package br.com.usjt.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.usjt.web.model.Item;
import br.com.usjt.web.model.Restaurante;
import br.com.usjt.web.model.Usuario;
import br.com.usjt.web.service.EnderecoMapper;
import br.com.usjt.web.service.RestauranteMapper;
import br.com.usjt.web.service.UsuarioMapper;
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

	public void createFuncionario(String cnpj, String tipo, String cpf, String nome, String dta_nascimento, String email, String ddd,
			String telefone, String genero, String senha, boolean createEndereco) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			//cria usuário. id do usuário é o ultimo criado
			restauranteMapper.createFuncionario(tipo, cpf, nome, dta_nascimento, email, ddd, telefone, genero, senha, createEndereco);
			session.commit();
		} finally {
			session.close();
		}
	}
	public void vincularFuncionarioRestaurante(String cnpj) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			restauranteMapper.vincularFuncionarioRestaurante(cnpj);
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
	
	public List<Item> getItensRestaurante(String cnpj) {
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
	
	public Item getItemById(int id) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			Item item = restauranteMapper.getItemById(id);
			return item;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}
	
	public void createItem(Item item) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			restauranteMapper.createItem(item.getCnpjRestaurante(), item.getCategoria(), item.getDetalhe(), 1, item.getNome(), item.getValor(), item.getStatus());
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Restaurante getRestauranteByCnpj(String cnpj) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			Restaurante restaurante = restauranteMapper.getRestauranteByCnpj(cnpj);
			return restaurante;
		} finally {
			session.close();
		}
	}

	public List<Restaurante> getHorarioRestauranteByCnpj(String cnpj) {
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
	
	public void updateItem(Item item) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);
			restauranteMapper.updateItem(item.getId(), item.getCnpjRestaurante(), item.getCategoria(), item.getDetalhe(), item.getNome(), item.getValor(), item.getStatus());
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
