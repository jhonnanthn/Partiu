package br.com.usjt.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.usjt.web.model.Usuario;
import br.com.usjt.web.service.UsuarioMapper;

public class UsuarioDAO {
	private SqlSessionFactory sqlSessionFactory;

	public UsuarioDAO() {
		sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
	}
	
	public void createUsuario(String tipo, String cpf, String nome, String dta_nascimento, String email, String ddd,
			String telefone, String genero, String senha, boolean createEndereco) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper UsuarioMapper = session.getMapper(UsuarioMapper.class);
			UsuarioMapper.createUsuario(tipo, cpf, nome, dta_nascimento, email, ddd, telefone, genero, senha, createEndereco);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	public void updateUsuario(Usuario usuario) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper UsuarioMapper = session.getMapper(UsuarioMapper.class);
			UsuarioMapper.updateUsuario(usuario);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	public List<Usuario> getUsuarioByParameter(String variavel, String valor) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper UsuarioMapper = session.getMapper(UsuarioMapper.class);
			List<Usuario> Usuario = UsuarioMapper.getUsuarioByParameter(variavel, valor);
			return Usuario;
		} finally {
			session.close();
		}
	}
	
	public Usuario getUsuarioOnLogin(String email, String senha) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper UsuarioMapper = session.getMapper(UsuarioMapper.class);
			Usuario Usuario = UsuarioMapper.getUsuarioOnLogin(email, senha);
			return Usuario;
		} finally {
			session.close();
		}
	}

	public void createEndereco(String logradouro, String numero, String complemento, String bairro, String cidade,
			String uf, String cep) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper UsuarioMapper = session.getMapper(UsuarioMapper.class);
			UsuarioMapper.createEndereco(logradouro, numero, complemento, bairro, cidade, uf, cep);
			session.commit();
		} finally {
			session.close();
		}
	}
}
